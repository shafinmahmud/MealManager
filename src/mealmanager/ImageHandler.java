
package mealmanager;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_highgui;
import java.awt.HeadlessException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author SHAFIN
 */
public class ImageHandler {

    private final Connection connRefIH;
    private PreparedStatement preparedstatementIH;
    private ResultSet resultsetIH;

    /**
     *
     * @param conn
     */
    public ImageHandler(Connection conn) {
        connRefIH = conn;
    }

    /**
     *
     */
    public void webCam() {
        Thread webcam;
        webcam = new Thread() {
            @Override
            public void run() {
                // Image convImage;
                // ImageIcon convIcon;

                opencv_highgui.CvCapture Capture = opencv_highgui.cvCreateCameraCapture(0);
                opencv_highgui.cvSetCaptureProperty(Capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 480);
                opencv_highgui.cvSetCaptureProperty(Capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 640);

                opencv_core.IplImage grabbedImage = opencv_highgui.cvQueryFrame(Capture);
                CanvasFrame frame = new CanvasFrame("WebCam");
                //JFrame frame = new JFrame("WebCam");
                while (frame.isVisible() && (grabbedImage = opencv_highgui.cvQueryFrame(Capture)) != null) {
                    frame.showImage(grabbedImage);
                }
                /* while (grabbedImage!= null) {
                 convImage = grabbedImage.getBufferedImage();
                 convIcon = new ImageIcon(convImage);
                 jlabel_frame.setIcon(convIcon);
                 jlabel_frame.drawImage();
                 repaint();
                 }*/
            }

        };
        webcam.start();
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public byte[] imgUpload() throws IOException {

        String filename;
        byte[] imgByte = null;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);

        File f = chooser.getSelectedFile();

        if (f != null) {
            filename = f.getAbsolutePath();

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (f.getName().endsWith(".jpg") || f.getName().endsWith(".gif")) {
                    //converting the selected image into bytecode
                    try {
                        File imgFile = new File(filename);
                        FileInputStream fis = new FileInputStream(imgFile);

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] buf = new byte[1024];

                        for (int readNum; (readNum = fis.read(buf)) != -1;) {
                            bos.write(buf, 0, readNum);
                        }
                        //assigning the converted image to bytearray to save into database
                        imgByte = bos.toByteArray();

                    } catch (FileNotFoundException e) {
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "select .jpg,.bmp or. gif");
                }
            }
        }
        return imgByte;
    }

    /**
     *
     * @param tablename
     * @param id
     * @param imgData
     */
    public void saveImageToDb(String tablename, int id, byte[] imgData) {

        try {
            String sql = "UPDATE " + tablename + " SET image=? WHERE ID=" + id;
            preparedstatementIH = connRefIH.prepareStatement(sql);
            preparedstatementIH.setBytes(1, imgData);
            preparedstatementIH.execute();

        } catch (SQLException | HeadlessException e) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                if (preparedstatementIH != null) {
                    preparedstatementIH.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *
     * @param id
     * @param tablename
     * @return
     */
    public ImageIcon retriveImageFromDb(int id, String tablename) {

        ImageIcon imgIcon = null;
        try {

            String sql = "SELECT image FROM " + tablename + " WHERE ID = " + id;
            preparedstatementIH = connRefIH.prepareStatement(sql);
            resultsetIH = preparedstatementIH.executeQuery();

            if (resultsetIH.next()) {
                byte[] img = resultsetIH.getBytes("image");
                if (img != null) {
                    imgIcon = new ImageIcon(img);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultsetIH != null) {
                    resultsetIH.close();
                }
                if (preparedstatementIH != null) {
                    preparedstatementIH.close();
                }
            } catch (SQLException e) {
            }
        }
        return imgIcon;
    }

}
