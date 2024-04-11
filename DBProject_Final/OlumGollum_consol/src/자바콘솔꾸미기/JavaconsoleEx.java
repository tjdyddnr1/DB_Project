package 자바콘솔꾸미기;
//======================================================
//JLabel.setIcon()으로 이미지 표시를 위한 import
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
//========================================================
public class JavaconsoleEx {
    public static final String black    = "\u001B[30m" ;
    public static final String red      = "\u001B[31m" ;
    public static final String green    = "\u001B[32m" ;
    public static final String yellow   = "\u001B[33m" ;
    public static final String blue     = "\u001B[34m" ;
    public static final String purple   = "\u001B[35m" ;
    public static final String cyan     = "\u001B[36;m" ;
    public static final String white     = "\u001B[37m" ;
    public static final String exit     = "\u001B[0m" ;

    public static void main(String[] args) throws IOException {
        JavaconsoleEx jc = new JavaconsoleEx();
        jc.PontColorFunc();
        jc.ImageInputfunc();
    }
    public void PontColorFunc(){
        System.out.println(blue+"=========================================================================="+exit);
        System.out.print(blue+"|"+exit);
        System.out.print(red+"                           OlumGollum_project                            "+exit);
        System.out.println(blue+"|"+exit);
        System.out.print(blue+"|"+exit);
        System.out.print(green+"                           OlumGollum_project                            "+exit);
        System.out.println(blue+"|"+exit);
        System.out.print(blue+"|"+exit);
        System.out.print(black+"                           OlumGollum_project                            "+exit);
        System.out.println(blue+"|"+exit);
        System.out.print(blue+"|"+exit);
        System.out.print(yellow+"                           OlumGollum_project                            "+exit);
        System.out.println(blue+"|"+exit);
        System.out.print(blue+"|"+exit);
        System.out.print(purple+"                           OlumGollum_project                            "+exit);
        System.out.println(blue+"|"+exit);
        System.out.print(blue+"|"+exit);
        System.out.print(cyan+"                           OlumGollum_project                            "+exit);
        System.out.println(blue+"|"+exit);
        System.out.print(blue+"|"+exit);
        System.out.print(white+"                           OlumGollum_project                            "+exit);
        System.out.println(blue+"|"+exit);
        System.out.println(blue+"=========================================================================="+exit);
    }
    //JLabel.setIcon()으로 이미지를 출력하는 함수
    public void ImageInputfunc() throws IOException {
        //이미지 파일 불러오는 부분
        File file = new File("C:\\My_All_Data\\java개발과 관련된 자료\\골룸.jpg");
        //이미지를 읽어 버퍼에 불러오는 부분.
        BufferedImage bufferedImage = ImageIO.read(file);

        //ImageIcon 객체 생성(버퍼의 이미지 파일 읽음).
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        //Image 변수에 저장.
        Image img = imageIcon.getImage();
        //Image 크기 변경(Update).
        Image updateImg = img.getScaledInstance(280, 180, Image.SCALE_SMOOTH);
        //출력을 위해 다시 ImageIcon객체로 변경.
        ImageIcon updateIcon = new ImageIcon(updateImg);
        //자바 프레임 객체 생성(액자)
        JFrame jFrame = new JFrame();
        //레이아웃 세팅
        jFrame.setLayout(new FlowLayout());
        //액자크기(화면크기) 조절
        jFrame.setSize(280, 250);
        //라벨 객체 생성
        JLabel jLabel = new JLabel();
        // 라벨에 아이콘(이미지) 설정
        jLabel.setIcon(updateIcon);
        // 라벨 설정(크기, 정렬...)
        jLabel.setBounds(210, 30, 165, 300);
        // 라벨 위치 조정(라벨이 우리가 생각하는 그 라벨인지, 라벨은 어디에 뜨는지 도통 모르겠다...바꿔도 안보임...)
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        //프레임에 컴포넌트 추가
        jFrame.add(jLabel);
        //프레임 보이기 지정
        jFrame.setVisible(true);
        //한번만 실행하고 JFrame 닫기.
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
