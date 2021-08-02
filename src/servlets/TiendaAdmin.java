package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import productos.Productos;
import usuarios.Usuarios;
import util.Validaciones;

/**
 * Servlet implementation class TiendaAdmin
 */
@MultipartConfig
@WebServlet("/TiendaAdmin")
public class TiendaAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// LA RUTA DONDE SE VAN A GUARDAR LAS IMAGENES
	// CAMBIAR ESTE PATH LUEGO.
	// Extensiones permitidas
	private String pathImages = "D:\\UNLZ\\Tecnologia Java\\Vet01\\WebContent\\images";
	private File uploads = new File(pathImages); 		
	private String[] extensiones = {".ico", ".png", ".jpg", ".jpeg"};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TiendaAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?tiendaAdmin=1";
		int idProducto;
		String nombre = "";
		String descripcion = "";
		double precio;
		int cantidad;
		String success = "";
		String errores = "";
		
		if (session.getAttribute("isAdmin") == null) {
			url = "index.jsp";
			response.sendRedirect(url);
			return;
		}
		
		if(request.getParameter("eliminar") != null) {
			idProducto = Integer.parseInt(request.getParameter("eliminar"));
			Productos.getInstance().deleteProducto(idProducto);
			success = "Producto eliminado.";
		}
		if(request.getParameter("modificar") != null) {
			idProducto = Integer.parseInt(request.getParameter("modificar"));
			request.setAttribute("modificarProducto", Productos.getInstance().getProducto(idProducto));
		}
		
		request.setAttribute("errores", errores);
		request.setAttribute("success", success);
		request.setAttribute("listaProductos", Productos.getInstance().getProductoLista());
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "panel.jsp?tiendaAdmin=1";
		int idProducto;
		String nombre = "";
		String descripcion = "";
		double precio;
		int cantidad;
		String success = "";
		String errores = "";
		
		
		// AGREGA UN NUEVO PRODUCTO. PROVIENE DESDE tienda_admin.jsp
		if(request.getParameter("agregarProducto") != null) {
			url += "&agregar=1";
			nombre = request.getParameter("nombre").trim();
			descripcion = request.getParameter("descripcion").trim();
			if(Validaciones.validaNumInt(request.getParameter("cantidad"))) {
				if(Validaciones.validaNumDouble(request.getParameter("precio"))) {
					if(Validaciones.validaCampo(nombre) && Validaciones.validaCampo(descripcion)) {
						cantidad = Integer.parseInt(request.getParameter("cantidad"));
						precio = Validaciones.stringToDouble(request.getParameter("precio"));
						if(Productos.getInstance().addProducto(nombre, precio, cantidad, descripcion)) {
							success = "Producto agregado.";							
						}else errores ="Producto del mismo nombre ya existente.";
					}else errores = "Nombre o descripcion inválidos.";
				}else errores = "Precio inválido.";
			}else errores = "Cantidad inválida.";
		}
		
		// MODIFICA UN PRODUCTO. PROVIENE DESDE tienda_admin.jsp
		if(request.getParameter("modificarProducto") != null) {
			url += "&modificar=1";
			if(Validaciones.validaNumInt(request.getParameter("modificarProducto"))) {
				idProducto = Integer.parseInt(request.getParameter("modificarProducto"));
				nombre = request.getParameter("nombre").trim();
				descripcion = request.getParameter("descripcion").trim();
				if(Validaciones.validaNumInt(request.getParameter("cantidad"))) {
					if(Validaciones.validaNumDouble(request.getParameter("precio"))) {
						if(Validaciones.validaCampo(nombre) && Validaciones.validaCampo(descripcion)) {
							cantidad = Integer.parseInt(request.getParameter("cantidad"));
							precio = Validaciones.stringToDouble(request.getParameter("precio"));
							if(!Productos.getInstance().updateNombre(nombre, idProducto)) {
								Productos.getInstance().updateProducto(idProducto, nombre, precio, cantidad, descripcion);
								success = "Producto modificado.";
							}else errores ="Producto del mismo nombre ya existente.";
						}else errores = "Nombre o descripcion inválidos.";
					}else errores = "Precio inválido.";
				}else errores = "Cantidad inválida.";
			}else errores = "Campos vacios.";
		}
		
		// CARGA DE IMAGEN A LA CARPETA INDICADA EN EL PATH Y A LA BASE DE DATOS.
		Part filepart = request.getPart("img");
		if(filepart.getContentType().contains("image")) {
			System.out.println("es una imagen");
			if(isExtension(filepart.getSubmittedFileName(), extensiones)) {
				// OBTENEMOS EL NOMBRE DEL PRODUCTO, Y SU EXTENSION, PARA GUARDARLA ASI EN LA BD LUEGO.
				String nombreImagen = nombre+getExtension(filepart.getSubmittedFileName(), extensiones);
				// GUARDAMOS EN LA CARPETA IMAGES
				String imgLink = saveImage(filepart,uploads,nombreImagen);
				Productos.getInstance().addImgLink(imgLink,nombre);
			}else System.out.println("extension de imagen no admitida");
		}else {
			System.out.println("NO es una imagen");
		}

		request.setAttribute("errores", errores);
		request.setAttribute("success", success);
		request.setAttribute("listaProductos", Productos.getInstance().getProductoLista());
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	
	
	
	
	
	
	
	/**
	 * Le pasamos el part donde tenemos la imagen y el File con el path donde se van a guardar.
	 * Guarda la imagen y nos devuelve la ruta absoluta, que debemos cargar a la tabla Productos
	 * Tambien le pasamos nombreImagen, para renombrar la imagen al mismo nombre del producto.
	 * @param part
	 * @param pathUploads
	 * @param nombreImagen
	 * @return
	 */
	private String saveImage(Part part, File pathUploads,String nombreImagen) {
		String pathAbsolute = "";
		try {
			//Path path = Paths.get(part.getSubmittedFileName());
			//renombramos la imagen.
			// esto es medio al pedo <----
			String fileName = nombreImagen;
			// en realidad no es medio al pedo, porque le quiero cambiar el nombre al archivo.
			InputStream input = part.getInputStream();
			if(input != null) {
				File file = new File(pathUploads,fileName);
				//pathAbsolute = file.getAbsolutePath();
				// En ves de devolver la url completa, devolvemos solo el nombre del archivo con su extension.
				pathAbsolute = nombreImagen;
				Files.copy(input, file.toPath());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pathAbsolute;
	}
	
	/**
	 * Comprueba que la extension sea la correcta, segun la declarara en extensiones[]
	 * @param fileName
	 * @param extensions
	 * @return true si tiene alguna, false si no la tiene.
	 */
	private boolean isExtension(String fileName, String[] extensiones) {
		for (String extens : extensiones) {
			if(fileName.toLowerCase().endsWith(extens)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Se le envia el fileName con el nombre completo del archivo, y el array de extensiones permitidas, 
	 * para comproboar con cual matchea.
	 * @param fileName
	 * @param extensiones
	 * @return Un string con la extension del archivo o vacio si no matchea con ninguna.
	 */
	private String getExtension(String fileName, String[] extensiones) {
		for (String extens : extensiones) {
			if(fileName.toLowerCase().endsWith(extens)) {
				return extens;
			}
		}
		return "";
	}
}
