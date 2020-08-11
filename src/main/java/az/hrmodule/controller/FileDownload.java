package az.hrmodule.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/download/*")
public class FileDownload extends HttpServlet {
    private final int ARBITARY_SIZE = 1048;
    private String filePath;

    public void init(){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("image/jpg");
       // resp.setHeader("Content-disposition", "attachment; filename=socar.png");

      String file_name=req.getPathInfo();
        System.out.println(file_name);
        try{
            InputStream in = new FileInputStream(new File(filePath+file_name));

            OutputStream out = resp.getOutputStream();

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
            out.close();
            in.close();
          }catch(Exception e){
      e.printStackTrace();
            }
    }
}

