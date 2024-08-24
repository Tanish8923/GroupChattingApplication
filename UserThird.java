package GroupChattingApplication;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.*;                                //use class - Calender
import java.text.*;                                //use class - Simple date format
import javax.swing.*;
import javax.swing.border.*;                       //border is a child package of swing //Use Border - EmptyBorder.

public class UserThird implements ActionListener , Runnable{

    JTextField msg;
    JPanel p2;
    static Box vertical = Box.createVerticalBox();                    //here we create a object of Box class and in box class we use .createVerticalBox() because we want to show our msg in vertical way.
    static JFrame f = new JFrame();
    static DataOutputStream dout;

    BufferedReader reader;
    BufferedWriter writer;
    String name = "Ishu";

    @SuppressWarnings("resource")
    public UserThird(){ 
        
        f.setLayout(null);

        JPanel p1 = new JPanel();                               //if you want to devide the frame and add something on frame then we can use panel we can say through panel we create a area on frame which can look different.
        p1.setBackground(new Color(9,94,84));             //here we create a object of Color class for using RGB colour.
        p1.setBounds(0,0 ,335,50 );
        f.add(p1);

        p1.setLayout(null);                                 //u need to make null , panel setLayout also for creating custom layout on panel by using setBounds().

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("GroupChattingApplication/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,13,25,25);
        p1.add(back);                                            //here we call add function through panel object beacuse we want to add this image on panel not on frame.
        back.addMouseListener(new MouseAdapter() {               //here we addMouseListener because we want to add action on image and inside that method we need to create object of MouseAdapter() class.
            public void mouseClicked(MouseEvent me){             //mouseClicked is method of MouseAdapter class which takes object of MouseEvent class.
                f.setVisible(false);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("GroupChattingApplication/icons/groupPic.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 40, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel pic = new JLabel(i6);
        pic.setBounds(45,5,50,40);
        p1.add(pic);

        JLabel name = new JLabel("Convo");
        name.setBounds(105, 8, 70,20);
        name.setFont(new Font("Railway" , Font.BOLD , 18));
        name.setForeground(Color.WHITE);
        p1.add(name);

        JLabel Status = new JLabel("Ishu , Tanish , Harshit");
        Status.setBounds(105, 28, 100,20);
        Status.setFont(new Font("Railway" , Font.BOLD , 10));
        Status.setForeground(Color.WHITE);
        p1.add(Status);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("GroupChattingApplication/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(220,10,30,30);
        p1.add(video);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("GroupChattingApplication/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(260,10,30,30);
        p1.add(phone);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("GroupChattingApplication/icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(12, 30, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(315,10,12,30);
        p1.add(morevert);

        p2 = new JPanel();
        p2.setBackground(Color.lightGray);
        p2.setBounds(0,55 ,335,420 );
        f.add(p2);

        msg = new JTextField();
        msg.setBounds(5,475,240,30);
        msg.setFont(new Font("Railway" , Font.BOLD , 20));
        f.add(msg);

        JButton send = new JButton("Send");
        send.setFont(new Font("Railway" , Font.BOLD ,18));
        send.setForeground(Color.WHITE);
        send.setBackground(new Color(9,94,84));
        send.setBounds(250,478,80,24);
        send.addActionListener(this);
        f.add(send);

        f.setSize(350,550);
        f.setLocation(760,60);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);

        try{
            Socket socket = new Socket("localhost", 2003);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent ae){
        String text = "<html><p>" + name + "</p><p>" + msg.getText() + "</p></html>";                           //getText() return value in String.
        JPanel p3 = formatLabel(text);                              //here we create a JPanel object p3 and in this we ruturn the output which is in JPanel by calling formatLabel() method.   

        p2.setLayout(new BorderLayout());                           //we can use different Layout for place components in different ways and we can also pass null as we see before. //here we use BorderLayout() so we create a object of BorderLayout class() ,"BorderLayout helps to place component in left , right , top , bottom , centre on panel or frame" 

        JPanel right = new JPanel(new BorderLayout());              //here we ctreate a panel and in panel we pass BorderLayout.
        right.add(p3 , BorderLayout.LINE_END);                      //Through this we add the text on panel'right' in LINE_END.

        vertical.add(right);                                         //here we add right(object of JPanel) in box object vertical. 
        vertical.add(Box.createVerticalStrut(5));             //here we create a vertical Stucture for text. like space between two msg in height.
        p2.add(vertical , BorderLayout.PAGE_START);                  //here we add the box on panel. and we set the location of first structbox on panel.

        try {
            writer.write(text);
            writer.write("\r\n");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg.setText("");
        

        f.repaint();                                                //we use repaint() , invalidate() , validate() for reload the frame. 
        f.invalidate();
        f.validate();

    }
    public static JPanel formatLabel(String text){                  //here we create a static method for create a format for box in which msg are show. 
        JPanel panel = new JPanel();                                //here we create a panel because in this method we need to return Panel.
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));    //BoxLayout takes two arguments one is "which place you add this box" and second is "side for these box 'so we want to allign these boxes one under the other' , so we choose Y_AXIS "

        JLabel out = new JLabel("<html><p style=\"width: 150px\">" + text + "</p></html>");

        out.setFont(new Font("Tahoma" , Font.PLAIN , 16));
        out.setBackground(Color.lightGray);
        out.setOpaque(true);                               //
        out.setBorder(new EmptyBorder(0,15,0,50));

        panel.add(out);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;

    }
    public void run(){
        try {
            String msg = "";
            while(true) {
                msg = reader.readLine();
                if (msg.contains(name)) {
                    continue;
                }
                
                JPanel panel = formatLabel(msg);
                
                JPanel left = new JPanel(new BorderLayout());
                left.setBackground(Color.WHITE);
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                
                p2.add(vertical, BorderLayout.PAGE_START);
                
                f.repaint();
                f.invalidate();
                f.validate();   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        UserThird third = new UserThird();
        Thread t1 = new Thread(third);
        t1.start();
    }
}
