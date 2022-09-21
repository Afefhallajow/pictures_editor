import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SeamCarver {

public int leanth;
    private BufferedImage pic;
    private String path;

    public SeamCarver(String path,int leanth) {
 this.path=path;
 this.leanth=leanth;
    }

    public SeamCarver(BufferedImage picture) {
        if (picture == null)
            throw new IllegalArgumentException("Picture can't be null.");

        pic =picture;
    }

    public BufferedImage picture() {
        return pic;
    }

    public int width() {
        return pic.getWidth();
    }

    public int height() {
        return pic.getHeight();
    }

    public double energy(int x, int y) {
        if (x >= width() || x < 0 || y >= height() || y < 0)
            throw new IllegalArgumentException("Coordinate outside of range.");

        int left = (x - 1) % width();
        int right = (x + 1) % width();
        int above = (y - 1) % height();
        int below = (y + 1) % height();

        if (above < 0)
            above += height();
        if (left < 0)
            left += width();

        double xGradient = gradient(left, right, y, true);
        double yGradient = gradient(above, below, x, false);

        return Math.sqrt(xGradient + yGradient);
    }

    private double gradient(int cOne, int cTwo, int direcCoor, boolean isHoriz) {
        Color coordOne;
        Color coordTwo;

        if (isHoriz) {
            coordOne = new Color(pic.getRGB(cOne, direcCoor));
            coordTwo = new Color(pic.getRGB(cTwo, direcCoor));
        } else {
            coordOne = new Color(pic.getRGB(direcCoor, cOne));
            coordTwo = new Color(pic.getRGB(direcCoor, cTwo));
        }

        double deltaR = coordTwo.getRed() - coordOne.getRed();
        double deltaG = coordTwo.getGreen() - coordOne.getGreen();
        double deltaB = coordTwo.getBlue() - coordOne.getBlue();

        return (deltaR * deltaR) + (deltaG * deltaG) + (deltaB * deltaB);
    }

    public int[] findVerticalSeam() {
        double[][] energyMatrix = new double[width()][height()];
        double[][] distTo = new double[width()][height()];
        int[][] edgeTo = new int[width()][height()];


        for (int i = 0; i < width(); i++)
            for (int j = 0; j < height(); j++) {
                energyMatrix[i][j] = energy(i, j);
                if (j == 0)
                    distTo[i][j] = energyMatrix[i][j];
                else
                    distTo[i][j] = Double.POSITIVE_INFINITY;
            }

        for (int i = 1; i < height(); i++)
            for (int j = 0; j < width(); j++) {
                double minEnergy = distTo[j][i - 1];
                int minCol = j;

                if (j != 0) // Upper left
                    if (distTo[j - 1][i - 1] < minEnergy) {
                        minEnergy = distTo[j - 1][i - 1];
                        minCol = j - 1;
                    }

                if (j != width() - 1)
                    if (distTo[j + 1][i - 1] < minEnergy) {
                        minEnergy = distTo[j + 1][i - 1];
                        minCol = j + 1;
                    }

                if (energyMatrix[j][i] + minEnergy < distTo[j][i]) {
                    distTo[j][i] = energyMatrix[j][i] + minEnergy;
                    edgeTo[j][i] = minCol;
                }
            }

        double minEnergyLastRow = Double.POSITIVE_INFINITY;
        int minColLastRow = 0;
        for (int i = 0; i < width(); i++)
            if (distTo[i][height() - 1] < minEnergyLastRow) {
                minEnergyLastRow = distTo[i][height() - 1];
                minColLastRow = i;
            }

        int[] vertSeam = new int[height()];
        for (int i = height() - 1; i >= 0; i--) {
            vertSeam[i] = minColLastRow;
            minColLastRow = edgeTo[minColLastRow][i];
        }

        return vertSeam;
    }

    public int[] findHorizontalSeam() {
        transpose();
        int[] horizSeam = findVerticalSeam();
        transpose();
        return horizSeam;
    }

    private void transpose() {
        BufferedImage transposePic = new BufferedImage(height(),width(), BufferedImage.TYPE_INT_RGB);


         for (int i = 0; i < height(); i++)
            for (int j = 0; j < width(); j++)
                transposePic.setRGB(i, j, pic.getRGB(j, i));

        pic = transposePic;
    }

    public void removeHorizontalSeam(int[] seam) {
        checkValidSeam(seam, true);
        transpose();
        removeVerticalSeam(seam);
        transpose();
    }

    public void removeVerticalSeam(int[] seam) {
        checkValidSeam(seam, false);
        BufferedImage seamedPic = new BufferedImage(width()-1,height(), BufferedImage.TYPE_INT_RGB);;
        for (int i = 0; i < height(); i++)
            for (int j = 0; j < width() - 1; j++) {
                if (j < seam[i])
                    seamedPic.setRGB(j, i, this.pic.getRGB(j, i));
                else
                    seamedPic.setRGB(j, i, this.pic.getRGB(j + 1, i));
            }

       this.pic = seamedPic;
    }
    public BufferedImage Energy_photo()
    {double[][] energyMatrix = new double[this.width()][this.height()];
    BufferedImage im1=new BufferedImage(width(),height(),BufferedImage.TYPE_INT_RGB) ;
    for (int i = 0; i < this.width(); i++)
        {
            for (int j = 0; j < this.height(); j++) {
                energyMatrix[i][j] = this.energy(i, j);
                im1.setRGB(i, j, (int) energyMatrix[i][j]);
            }   }
        return im1;
    }
public void SeamCrave1() throws IOException {   File file = new File(this.path);

 BufferedImage image= ImageIO.read(file);
 this.pic=image;
 System.out.println(image.getWidth());
int x=(image.getHeight()*this.leanth/100);
int y=(image.getWidth()*this.leanth/100);
 for (int i=0;i<=x;i++) {
     int[] s = this.findVerticalSeam();

     this.removeVerticalSeam(s);
     System.out.println(this.picture().getWidth());
 }/**
 for (int i=0;i<=y;i++)
 {
    int[] s1= this.findHorizontalSeam();
 this.removeHorizontalSeam(s1);
 System.out.println(this.picture().getHeight());}
**/
 BufferedImage im=new BufferedImage(this.width(),this.height(),BufferedImage.TYPE_INT_RGB);
 BufferedImage im1=new BufferedImage(this.width(),this.height(),BufferedImage.TYPE_INT_RGB);
 System.out.println(this.picture().getHeight());
 im=this.pic;
 double[][] energyMatrix = new double[this.width()][this.height()];
 for (int i = 0; i < this.width(); i++)
 {
 for (int j = 0; j < this.height(); j++) {
 energyMatrix[i][j] = this.energy(i, j);
 im1.setRGB(i, j, (int) energyMatrix[i][j]);
 }   }
 File output=new File("C:\\Users\\HP\\Downloads\\Compressed\\miltimedia\\src\\a.png");
 ImageIO.write(im,"png",output);
 File output1=new File("C:\\Users\\HP\\Downloads\\Compressed\\miltimedia\\src\\b.png");
 ImageIO.write(im1,"png",output1);

}
    public void Save (String path1,BufferedImage image) throws IOException {
        File output1=new File(path1);

        System.out.println(path1);
        System.out.println(pic.getHeight());

                ImageIO.write(image,"png",output1);

    }
    private void checkValidSeam(int[] seam, boolean isHoriz) {
        if (seam == null)
            throw new IllegalArgumentException("No null arguments.");


        if (isHoriz) // Horizontal seam
        {

            if (seam.length != width())
                throw new IllegalArgumentException("Array is wrong length.");

            for (int i = 0; i < width(); i++) {

                if (seam[i] < 0 || seam[i] >= height())
                    throw new IllegalArgumentException("Invalid seam.");


                if (i == 0)
                    continue;
                if (Math.abs(seam[i] - seam[i - 1]) > 1)
                    throw new IllegalArgumentException("Invalid seam.");
            }

            if (height() == 1)
                throw new IllegalArgumentException("Height must be > 1px.");
        } else // Vertical seam
        {

            if (seam.length != height())
                throw new IllegalArgumentException("Array is wrong length.");

            for (int i = 0; i < height(); i++) {

                if (seam[i] < 0 || seam[i] >= width())
                    throw new IllegalArgumentException("Invalid seam.");


                if (i == 0)
                    continue;
                if (Math.abs(seam[i] - seam[i - 1]) > 1)
                    throw new IllegalArgumentException("Invalid seam.");
            }

            if (width() == 1)
                throw new IllegalArgumentException("Width must be > 1px.");
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\HP\\Desktop\\miltimedia\\apple.jpg");
/**
 BufferedImage image= ImageIO.read(file);
 System.out.println(image.getWidth());
 SeamCarver picCarve = new SeamCarver(image);

 for (int i=0;i<=50;i++)
 {int[] s= picCarve.findVerticalSeam();
 picCarve.removeVerticalSeam(s);
 //    System.out.println(picCarve.picture().getWidth());
 int[] s1= picCarve.findHorizontalSeam();
 picCarve.removeHorizontalSeam(s1);
 System.out.println(picCarve.picture().getHeight());}

 BufferedImage im=new BufferedImage(picCarve.width(),picCarve.height(),BufferedImage.TYPE_INT_RGB);
 BufferedImage im1=new BufferedImage(picCarve.width(),picCarve.height(),BufferedImage.TYPE_INT_RGB);
 System.out.println(picCarve.picture().getHeight());
 im=picCarve.pic;
 double[][] energyMatrix = new double[picCarve.width()][picCarve.height()];
 for (int i = 0; i < picCarve.width(); i++)
 {
 for (int j = 0; j < picCarve.height(); j++) {
 energyMatrix[i][j] = picCarve.energy(i, j);
 im1.setRGB(i, j, (int) energyMatrix[i][j]);
 }   }
 File output=new File("C:\\Users\\HP\\Desktop\\a\\a.png");
 ImageIO.write(im,"png",output);
 File output1=new File("C:\\Users\\HP\\Desktop\\a\\b.png");
 ImageIO.write(im1,"png",output1);
 **/
    }
}

