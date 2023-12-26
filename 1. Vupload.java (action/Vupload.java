package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class Vupload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String uname = null;
            String pass = null;
            String dob = null;
            String email = null;
            String mob = null;
            String loc = null;
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            if (!isMultipartContent) {
                return;
            }
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> fields = upload.parseRequest(new ServletRequestContext(request));
                Iterator<FileItem> it = fields.iterator();
                if (!it.hasNext()) {
                    return;
                }
                while (it.hasNext()) {
                    FileItem fileItem = it.next();

                    if (fileItem.getFieldName().equals("rname")) {
                        uname = fileItem.getString();
                        System.out.println(uname);
                    } else if (fileItem.getFieldName().equals("rpass")) {
                        pass = fileItem.getString();
                        System.out.println(pass);
                    } else if (fileItem.getFieldName().equals("dob")) {
                        dob = fileItem.getString();
                        System.out.println(dob);
                    } else if (fileItem.getFieldName().equals("email")) {
                        email = fileItem.getString();
                        System.out.println(email);
                    } else if (fileItem.getFieldName().equals("mob")) {
                        mob = fileItem.getString();
                        System.out.println(mob);
                    } else if (fileItem.getFieldName().equals("loc")) {
                        loc = fileItem.getString();
                        System.out.println(loc);
                    } else {
                    }
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {
                    } else {
                        try {
                            Connection cn = Db.getConnection();
                            PreparedStatement ps = cn.prepareStatement("insert into vendor(image, iname, name, pass, dob, email, mob, loc, seckey, request)values(?,?,?,?,?,?,?,?,?,?)");
                            ps.setBinaryStream(1, fileItem.getInputStream());
                            ps.setString(2, fileItem.getName());
                            ps.setString(3, uname);
                            ps.setString(4, pass);
                            ps.setString(5, dob);
                            ps.setString(6, email);
                            ps.setString(7, mob);
                            ps.setString(8, loc);
                            ps.setString(9,"Generate Secret Key");
                            ps.setString(10,"NO");
                            int i = ps.executeUpdate();
                            if (i == 1) {
                                response.sendRedirect("vreg.jsp?msg=Registered Successfully");
                            } else {
                                response.sendRedirect("vreg.jsp?msgg=Registered Failed");
                            }
                            cn.close();

                        } catch (Exception e) {
                            System.out.println("Exception Error in Upload "+e.getMessage());
                        }
                    }

                }
            } catch (FileUploadException e) {
            } catch (Exception ex) {
                Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
