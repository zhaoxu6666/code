package fk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ImagePanel extends JPanel{
    private Image image;
    private int width;
    private int height;
    ImagePanel(Image image, int width, int height){
        this.image = image;
        this.width = width;
        this.height = height;
        //setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, width, height,this);
    }
}

class CustomImgPanel extends JPanel{
    private int width = 0;
    private int height = 0;
    private String imgPath = "";

    CustomImgPanel(int width, int height, String imgPath){
        this.width = width;
        this.height = height;
        this.imgPath = imgPath;
        setSize(width, height);
        setVisible(true);
    }

    CustomImgPanel(double width, double height, String imgPath){
        this.width = (int)width;
        this.height = (int)height;
        this.imgPath = imgPath;
        setSize(this.width, this.height);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics gs) {
        Graphics2D g = (Graphics2D)gs;
        super.paintComponent(g);

        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imgPath));

        g.drawImage(image, 0, 0, width, height, this);
    }
}

class JFramDemo extends JFrame{
    private static JFramDemo jFramDemo;
    private static HashMap<String, JComponent> tempHashMap = new HashMap<>();
    private static HashMap<String, JComponent> humiHashMap = new HashMap<>();
    private static HashMap<String, JComponent> volHashMap = new HashMap<>();
    private static HashMap<String, JComponent> lighashmap = new HashMap<>();

    private JFramDemo(){
        setSize(1265, 680);
        setTitle("Server");
        setLocationRelativeTo(null);//居中显示
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //image
        CustomImgPanel background = new CustomImgPanel(getWidth(), getHeight(), "wt1.png");

        Container c = getContentPane();

        c.add(background, -1);

        background.setLayout(new BorderLayout());

        JPanel jPanel3 = new JPanel(new BorderLayout());  //创建面板，这个类似于HTML的div标签 底层的信息显示

        JPanel jPanel1 = new JPanel(new BorderLayout());  //隐藏信息显示
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        //background add JPanel
        background.add(jPanel3,BorderLayout.SOUTH);
        background.add(jPanel1);

        //信息隐藏部分1
        JPanel jPanel111 = new JPanel(new BorderLayout());
        jPanel111.setBounds(30,75,230,80);
        jPanel111.setBackground(null);  //Transparent conf
        jPanel111.setOpaque(false);
        jPanel111.setVisible(false);  //hide conf
        Flicker.setjPanel1(jPanel111);
        jPanel1.add(jPanel111);
        hide1(jPanel111, Locations.LOCATION1.getName());
        //信息隐藏部分2
        JPanel jPanel112 = new JPanel(new BorderLayout());
        jPanel112.setBounds(670,55,230,80);
        jPanel112.setBackground(null);  //Transparent conf
        jPanel112.setOpaque(false);
        jPanel112.setVisible(false);  //hide conf
        Flicker.setjPanel2(jPanel112);
        jPanel1.add(jPanel112);
        hide2(jPanel112, Locations.LOCATION2.getName());
        //信息隐藏部分3
        JPanel jPanel113 = new JPanel(new BorderLayout());
        jPanel113.setBounds(30,430,230,80);
        jPanel113.setBackground(null);  //Transparent conf
        jPanel113.setOpaque(false);
        jPanel113.setVisible(false);  //hide conf
        Flicker.setjPanel3(jPanel113);
        jPanel1.add(jPanel113);
        hide1(jPanel113, Locations.LOCATION3.getName());
        //信息隐藏部分4
        JPanel jPanel114 = new JPanel(new BorderLayout());
        jPanel114.setBounds(680,450,230,80);
        jPanel114.setBackground(null);  //Transparent conf
        jPanel114.setOpaque(false);
        jPanel114.setVisible(false);  //hide conf
        Flicker.setjPanel4(jPanel114);
        jPanel1.add(jPanel114);
        hide2(jPanel114, Locations.LOCATION4.getName());
        //信息隐藏部分5
        JPanel jPanel115 = new JPanel(new BorderLayout());
        jPanel115.setBounds(970,260,230,80);
        jPanel115.setBackground(null);  //Transparent conf
        jPanel115.setOpaque(false);
        jPanel115.setVisible(false);  //hide conf
        Flicker.setjPanel5(jPanel115);
        jPanel1.add(jPanel115);
        hide3(jPanel115, Locations.LOCATION5.getName());


        //JPanel add JPanel
	    JPanel jPanel11 = new JPanel(new BorderLayout());
        jPanel3.add(jPanel11,BorderLayout.WEST);
        addOnJFrameHorizontal1(jPanel11, Locations.LOCATION1.getName());

        //一层套一层
        JPanel jPanel21 = new JPanel(new BorderLayout());
        jPanel11.add(jPanel21,BorderLayout.EAST);
        addOnJFrameHorizontal2(jPanel21, Locations.LOCATION2.getName());


	    JPanel jPanel31 = new JPanel(new BorderLayout());
        jPanel21.add(jPanel31, BorderLayout.EAST);
        addOnJFrameHorizontal3(jPanel31, Locations.LOCATION3.getName());


        JPanel jPanel41 = new JPanel(new BorderLayout());
        jPanel31.add(jPanel41, BorderLayout.EAST);
        addOnJFrameHorizontal4(jPanel41, Locations.LOCATION4.getName());


        JPanel jPanel51 = new JPanel(new BorderLayout());
        jPanel41.add(jPanel51, BorderLayout.EAST);
        addOnJFrameHorizontal5(jPanel51, Locations.LOCATION5.getName());

        setVisible(true);
    }

    //start
    public static JFramDemo getInstance(){
        if(jFramDemo == null){
            jFramDemo = new JFramDemo();
        }
        return jFramDemo;
    }

    //info
    private void addOnJFrameHorizontal1(JPanel jPanel, String name){
        JLabel jLabel = new JLabel("  设备"+name+"   ");
        jLabel.setBackground(Color.BLACK);
        jLabel.setFont(new Font("宋体", Font.BOLD, 20));
        
        JButton confirmButton = new JButton("阈值修改");
//        confirmButton.setFont(new Font("宋体", Font.BOLD, 10));
        confirmButton.addActionListener(new ActionListener() {
     	 
        	@Override
			public void actionPerformed(ActionEvent arg0) {
				//按钮调用修改警告值界面
				change1(name);
			}
        });
        
        JLabel jLabelTemp = new JLabel("  温度: ");
        jLabelTemp.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldTemp = new JTextField("99");
	jTextFieldTemp.setFont(new Font("宋体", Font.BOLD, 16));

        jTextFieldTemp.setEditable(false);
        tempHashMap.put(name, jTextFieldTemp);

        JLabel jLabel1Hum = new JLabel("  湿度: ");
        jLabel1Hum.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldHum = new JTextField("99");
	jTextFieldHum.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldHum.setEditable(false);
        humiHashMap.put(name, jTextFieldHum);

        JLabel jLabel1Vol = new JLabel("噪音预警: ");
        jLabel1Vol.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldVol = new JTextField("噪音");
	jTextFieldVol.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldVol.setEditable(false);
        volHashMap.put(name, jTextFieldVol);

        
        JLabel jLabel1Lig = new JLabel("强光预警: ");
        jLabel1Lig.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldLig = new JTextField("强光");
	jTextFieldLig.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldLig.setEditable(false);
        lighashmap.put(name, jTextFieldLig);

	
        //jPanel.add(jLabel);
	jPanel.add(jLabelTemp);jPanel.add(jTextFieldTemp);
        jPanel.add(jLabel1Hum);jPanel.add(jTextFieldHum);jPanel.add(jLabel1Vol);
        jPanel.add(jTextFieldVol);jPanel.add(jLabel1Lig);jPanel.add(jTextFieldLig);
	
	Box box1 = Box.createHorizontalBox();
	Box box2 = Box.createHorizontalBox();
	Box box3 = Box.createHorizontalBox();
	box1.setBackground(Color.black);
	box1.add(jLabel);box1.add(confirmButton);
	box2.add(jLabelTemp);box2.add(jTextFieldTemp);
	box2.add(jLabel1Vol);box2.add(jTextFieldVol);
	
	box3.add(jLabel1Hum);box3.add(jTextFieldHum);
	box3.add(jLabel1Lig);box3.add(jTextFieldLig);
	Box box = Box.createVerticalBox();
	box.add(box1);
	box.add(box2);
	box.add(box3);
	box.setBackground(Color.BLACK);
    	jPanel.add(box);
    }
    private void addOnJFrameHorizontal2(JPanel jPanel, String name){
        JLabel jLabel = new JLabel("    设备"+name+"   ");
        jLabel.setBackground(Color.BLACK);
        jLabel.setFont(new Font("宋体", Font.BOLD, 20));
        
        JButton confirmButton = new JButton("阈值修改");
//        confirmButton.setFont(new Font("宋体", Font.BOLD, 10));
        confirmButton.addActionListener(new ActionListener() {
     	 
        	@Override
			public void actionPerformed(ActionEvent arg0) {
				//按钮调用修改警告值界面
				change2(name);
			}
        });
        
        JLabel jLabelTemp = new JLabel("    温度: ");
        jLabelTemp.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldTemp = new JTextField("99");
	jTextFieldTemp.setFont(new Font("宋体", Font.BOLD, 16));

        jTextFieldTemp.setEditable(false);
        tempHashMap.put(name, jTextFieldTemp);

        JLabel jLabel1Hum = new JLabel("    湿度: ");
        jLabel1Hum.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldHum = new JTextField("99");
	jTextFieldHum.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldHum.setEditable(false);
        humiHashMap.put(name, jTextFieldHum);

        JLabel jLabel1Vol = new JLabel("加速度: ");
        jLabel1Vol.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldVol = new JTextField("99");
	jTextFieldVol.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldVol.setEditable(false);
        volHashMap.put(name, jTextFieldVol);

        
        JLabel jLabel1Lig = new JLabel("强光预警: ");
        jLabel1Lig.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldLig = new JTextField("强光");
	jTextFieldLig.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldLig.setEditable(false);
        lighashmap.put(name, jTextFieldLig);

	
        //jPanel.add(jLabel);
	jPanel.add(jLabelTemp);jPanel.add(jTextFieldTemp);
        jPanel.add(jLabel1Hum);jPanel.add(jTextFieldHum);jPanel.add(jLabel1Vol);
        jPanel.add(jTextFieldVol);jPanel.add(jLabel1Lig);jPanel.add(jTextFieldLig);
	
	Box box1 = Box.createHorizontalBox();
	Box box2 = Box.createHorizontalBox();
	Box box3 = Box.createHorizontalBox();
	box1.setBackground(Color.black);
	box1.add(jLabel);box1.add(confirmButton);
	box2.add(jLabelTemp);box2.add(jTextFieldTemp);
	box2.add(jLabel1Vol);box2.add(jTextFieldVol);
	
	box3.add(jLabel1Hum);box3.add(jTextFieldHum);
	box3.add(jLabel1Lig);box3.add(jTextFieldLig);
	Box box = Box.createVerticalBox();
	box.add(box1);
	box.add(box2);
	box.add(box3);
	box.setBackground(Color.BLACK);
    	jPanel.add(box);
    }
    private void addOnJFrameHorizontal3(JPanel jPanel, String name){
        JLabel jLabel = new JLabel("    设备"+name+"   ");
        jLabel.setBackground(Color.BLACK);
        jLabel.setFont(new Font("宋体", Font.BOLD, 20));
        
        JButton confirmButton = new JButton("阈值修改");
//        confirmButton.setFont(new Font("宋体", Font.BOLD, 10));
        confirmButton.addActionListener(new ActionListener() {
     	 
        	@Override
			public void actionPerformed(ActionEvent arg0) {
				//按钮调用修改警告值界面
				change3(name);
			}
        });
        
        JLabel jLabelTemp = new JLabel("    温度: ");
        jLabelTemp.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldTemp = new JTextField("99");
	jTextFieldTemp.setFont(new Font("宋体", Font.BOLD, 16));

        jTextFieldTemp.setEditable(false);
        tempHashMap.put(name, jTextFieldTemp);

        JLabel jLabel1Hum = new JLabel("    湿度: ");
        jLabel1Hum.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldHum = new JTextField("99");
	jTextFieldHum.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldHum.setEditable(false);
        humiHashMap.put(name, jTextFieldHum);

        JLabel jLabel1Vol = new JLabel("气体污染强度: ");
        jLabel1Vol.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldVol = new JTextField("99");
	jTextFieldVol.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldVol.setEditable(false);
        volHashMap.put(name, jTextFieldVol);

        
        JLabel jLabel1Lig = new JLabel("震动预警: ");
        jLabel1Lig.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldLig = new JTextField("震动");
	jTextFieldLig.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldLig.setEditable(false);
        lighashmap.put(name, jTextFieldLig);

	
        //jPanel.add(jLabel);
	jPanel.add(jLabelTemp);jPanel.add(jTextFieldTemp);
        jPanel.add(jLabel1Hum);jPanel.add(jTextFieldHum);jPanel.add(jLabel1Vol);
        jPanel.add(jTextFieldVol);jPanel.add(jLabel1Lig);jPanel.add(jTextFieldLig);
	
	Box box1 = Box.createHorizontalBox();
	Box box2 = Box.createHorizontalBox();
	Box box3 = Box.createHorizontalBox();
	box1.setBackground(Color.black);
	box1.add(jLabel);box1.add(confirmButton);
	box2.add(jLabelTemp);box2.add(jTextFieldTemp);
	box2.add(jLabel1Vol);box2.add(jTextFieldVol);
	
	box3.add(jLabel1Hum);box3.add(jTextFieldHum);
	box3.add(jLabel1Lig);box3.add(jTextFieldLig);
	Box box = Box.createVerticalBox();
	box.add(box1);
	box.add(box2);
	box.add(box3);
	box.setBackground(Color.BLACK);
    	jPanel.add(box);
    }
    private void addOnJFrameHorizontal4(JPanel jPanel, String name){
        JLabel jLabel = new JLabel("    设备"+name+"   ");
        jLabel.setBackground(Color.BLACK);
        jLabel.setFont(new Font("宋体", Font.BOLD, 20));
        
        JButton confirmButton = new JButton("阈值修改");
//        confirmButton.setFont(new Font("宋体", Font.BOLD, 10));
        confirmButton.addActionListener(new ActionListener() {
     	 
        	@Override
			public void actionPerformed(ActionEvent arg0) {
				//按钮调用修改警告值界面
				change4(name);
			}
        });
        
        JLabel jLabelTemp = new JLabel("    温度: ");
        jLabelTemp.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldTemp = new JTextField("99");
	jTextFieldTemp.setFont(new Font("宋体", Font.BOLD, 16));

        jTextFieldTemp.setEditable(false);
        tempHashMap.put(name, jTextFieldTemp);

        JLabel jLabel1Hum = new JLabel("    湿度: ");
        jLabel1Hum.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldHum = new JTextField("99");
	jTextFieldHum.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldHum.setEditable(false);
        humiHashMap.put(name, jTextFieldHum);

        JLabel jLabel1Vol = new JLabel("噪音预警: ");
        jLabel1Vol.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldVol = new JTextField("噪音");
	jTextFieldVol.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldVol.setEditable(false);
        volHashMap.put(name, jTextFieldVol);

        
        JLabel jLabel1Lig = new JLabel("火光强度: ");
        jLabel1Lig.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldLig = new JTextField("99");
	jTextFieldLig.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldLig.setEditable(false);
        lighashmap.put(name, jTextFieldLig);

	
        //jPanel.add(jLabel);
	jPanel.add(jLabelTemp);jPanel.add(jTextFieldTemp);
        jPanel.add(jLabel1Hum);jPanel.add(jTextFieldHum);jPanel.add(jLabel1Vol);
        jPanel.add(jTextFieldVol);jPanel.add(jLabel1Lig);jPanel.add(jTextFieldLig);
	
	Box box1 = Box.createHorizontalBox();
	Box box2 = Box.createHorizontalBox();
	Box box3 = Box.createHorizontalBox();
	box1.setBackground(Color.black);
	box1.add(jLabel);box1.add(confirmButton);
	box2.add(jLabelTemp);box2.add(jTextFieldTemp);
	box2.add(jLabel1Vol);box2.add(jTextFieldVol);
	
	box3.add(jLabel1Hum);box3.add(jTextFieldHum);
	box3.add(jLabel1Lig);box3.add(jTextFieldLig);
	Box box = Box.createVerticalBox();
	box.add(box1);
	box.add(box2);
	box.add(box3);
	box.setBackground(Color.BLACK);
    	jPanel.add(box);
    }
    private void addOnJFrameHorizontal5(JPanel jPanel, String name){
        JLabel jLabel = new JLabel("    设备"+name+"   ");
        jLabel.setBackground(Color.BLACK);
        jLabel.setFont(new Font("宋体", Font.BOLD, 20));

        JButton confirmButton = new JButton("阈值修改");
//        confirmButton.setFont(new Font("宋体", Font.BOLD, 10));
        confirmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                //按钮调用修改警告值界面
                change1(name);
            }
        });

        JLabel jLabelTemp = new JLabel("    温度: ");
        jLabelTemp.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldTemp = new JTextField("99");
        jTextFieldTemp.setFont(new Font("宋体", Font.BOLD, 16));

        jTextFieldTemp.setEditable(false);
        tempHashMap.put(name, jTextFieldTemp);

        JLabel jLabel1Hum = new JLabel("    湿度: ");
        jLabel1Hum.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldHum = new JTextField("99");
        jTextFieldHum.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldHum.setEditable(false);
        humiHashMap.put(name, jTextFieldHum);

        JLabel jLabel1Vol = new JLabel("震动预警: ");
        jLabel1Vol.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldVol = new JTextField("震动");
        jTextFieldVol.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldVol.setEditable(false);
        volHashMap.put(name, jTextFieldVol);


        JLabel jLabel1Lig = new JLabel("倾斜预警: ");
        jLabel1Lig.setFont(new Font("宋体", Font.BOLD, 17));
        JTextField jTextFieldLig = new JTextField("倾斜");
        jTextFieldLig.setFont(new Font("宋体", Font.BOLD, 16));
        jTextFieldLig.setEditable(false);
        lighashmap.put(name, jTextFieldLig);


        //jPanel.add(jLabel);
        jPanel.add(jLabelTemp);jPanel.add(jTextFieldTemp);
        jPanel.add(jLabel1Hum);jPanel.add(jTextFieldHum);jPanel.add(jLabel1Vol);
        jPanel.add(jTextFieldVol);jPanel.add(jLabel1Lig);jPanel.add(jTextFieldLig);

        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        box1.setBackground(Color.black);
        box1.add(jLabel);box1.add(confirmButton);
        box2.add(jLabelTemp);box2.add(jTextFieldTemp);
        box2.add(jLabel1Vol);box2.add(jTextFieldVol);

        box3.add(jLabel1Hum);box3.add(jTextFieldHum);
        box3.add(jLabel1Lig);box3.add(jTextFieldLig);
        Box box = Box.createVerticalBox();
        box.add(box1);
        box.add(box2);
        box.add(box3);
        box.setBackground(Color.BLACK);
        jPanel.add(box);
    }

    //hide
    private void hide1(JPanel jPanel, String name){
        JLabel jLabel = new JLabel("    储藏室：");
        jLabel.setFont(new Font("宋体", Font.BOLD, 25));
        jLabel.setForeground(Color.RED);
        JLabel jLabel1 = new JLabel("设备"+name);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 25));
        jLabel1.setForeground(Color.RED);

        Box box1 = Box.createHorizontalBox();
        box1.add(jLabel);box1.add(jLabel1);
        jPanel.add(box1);
    }
    private void hide2(JPanel jPanel, String name){
        JLabel jLabel = new JLabel("    耳光室：");
        jLabel.setFont(new Font("宋体", Font.BOLD, 25));
        jLabel.setForeground(Color.RED);
        JLabel jLabel1 = new JLabel("设备"+name);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 25));
        jLabel1.setForeground(Color.RED);

        Box box1 = Box.createHorizontalBox();
        box1.add(jLabel);box1.add(jLabel1);
        jPanel.add(box1);
    }
    private void hide3(JPanel jPanel, String name){
        JLabel jLabel = new JLabel("    舞台：");
        jLabel.setFont(new Font("宋体", Font.BOLD, 25));
        jLabel.setForeground(Color.RED);
        JLabel jLabel1 = new JLabel("设备"+name);
        jLabel1.setFont(new Font("宋体", Font.BOLD, 25));
        jLabel1.setForeground(Color.RED);

        Box box1 = Box.createHorizontalBox();
        box1.add(jLabel);box1.add(jLabel1);
        jPanel.add(box1);
    }

    //阈值修改界面
    private void change1(String name) {
    	// 创建 JFrame 实例
        JFrame frame = new JFrame("设备"+name+"阈值修改");
        // Setting the width and height of frame
        frame.setSize(350, 170);
        frame.setLocationRelativeTo(null);  //居中显示
        //只关闭此窗口，父窗口没影响
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents1(panel,frame,name);

        // 设置界面可见
        frame.setVisible(true);
    }
    private static void placeComponents1(JPanel panel,JFrame frame,String name) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel tempLabel = new JLabel("请输入温度阈值：");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        tempLabel.setBounds(50,20,160,25);
        panel.add(tempLabel);

        /*
         * 创建文本域用于温度改变值输入
         */
        JTextField tempText = new JTextField(20);
        tempText.setBounds(180,20,80,25);
        panel.add(tempText);

        //温度的单位显示
        JLabel tempUnit = new JLabel("°C");
        tempUnit.setBounds(270,20,25,25);
        panel.add(tempUnit);


        JLabel humiLabel = new JLabel("请输入湿度阈值：");
        humiLabel.setBounds(50,50,160,25);
        panel.add(humiLabel);

        /*
         * 创建文本域用于湿度改变值输入
         */
        JTextField humiText = new JTextField(20);
        humiText.setBounds(180,50,80,25);
        panel.add(humiText);

        //湿度单位显示
        JLabel humiUnit = new JLabel("%RH");
        humiUnit.setBounds(270,50,40,25);
        panel.add(humiUnit);

        // 创建确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setBounds(85, 90, 60, 25);
        confirmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                String t = tempText.getText();
                String h = humiText.getText();

                try {
                    Static.t = Integer.parseInt(t);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                try {
                    Static.h = Integer.parseInt(h);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                Static.setchange(name);  //修改的值传到静态类中的 变量之后，通过次函数进行不同设备的t，h值的设置

                System.out.println("修改之后的t："+name+"**********"+Static.getT(name));
                System.out.println("修改之后的h："+name+"**********"+Static.getH(name));

                frame.dispose();
            }
        });
        panel.add(confirmButton);

        // 创建取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(190, 90, 60, 25);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                frame.dispose();
            }
        });
        panel.add(cancelButton);
    }
    
    private void change2(String name) {
    	// 创建 JFrame 实例
        JFrame frame = new JFrame("设备"+name+"阈值修改");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);  //居中显示
        //只关闭此窗口，父窗口没影响
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents2(panel,frame,name);

        // 设置界面可见
        frame.setVisible(true);
    }
    private static void placeComponents2(JPanel panel,JFrame frame,String name) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel tempLabel = new JLabel("请输入温度阈值：");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        tempLabel.setBounds(50,20,160,25);
        panel.add(tempLabel);

        /* 
         * 创建文本域用于温度改变值输入
         */
        JTextField tempText = new JTextField(20);
        tempText.setBounds(180,20,80,25);
        panel.add(tempText);
        
        //温度的单位显示
        JLabel tempUnit = new JLabel("°C");
        tempUnit.setBounds(270,20,25,25);
        panel.add(tempUnit);

        
        JLabel humiLabel = new JLabel("请输入湿度阈值：");
        humiLabel.setBounds(50,50,160,25);
        panel.add(humiLabel);

        /* 
         * 创建文本域用于湿度改变值输入
         */
        JTextField humiText = new JTextField(20);
        humiText.setBounds(180,50,80,25);
        panel.add(humiText);

        //湿度单位显示
        JLabel humiUnit = new JLabel("%RH");
        humiUnit.setBounds(270,50,40,25);
        panel.add(humiUnit);
        
        JLabel aLabel = new JLabel("请输入加速度阈值：");
        aLabel.setBounds(50,80,160,25);
        panel.add(aLabel);

        /* 
         * 创建文本域用于湿度改变值输入
         */
        JTextField aText = new JTextField(20);
        aText.setBounds(180,80,80,25);
        panel.add(aText);

        //湿度单位显示
        JLabel aUnit = new JLabel("m/s^2");
        aUnit.setBounds(270,80,40,25);
        panel.add(aUnit);
        
        // 创建确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setBounds(85, 120, 60, 25);
        confirmButton.addActionListener(new ActionListener() {
        	 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String t = tempText.getText();
				String h = humiText.getText();
				String a = aText.getText();
				
				try {
				    Static.t = Integer.parseInt(t);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				try {
				    Static.h = Integer.parseInt(h);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				try {
				    Static.a = Double.parseDouble(a);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				Static.setchange(name);  //修改的值传到静态类中的 变量之后，通过次函数进行不同设备的t，h值的设置
				
				System.out.println("修改之后的t："+name+"**********"+Static.getT(name));
				System.out.println("修改之后的h："+name+"**********"+Static.getH(name));
				
				frame.dispose();
			}
        });
        panel.add(confirmButton);
        
     // 创建取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(190, 120, 60, 25);
        cancelButton.addActionListener(new ActionListener() {
       	 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
        });
        panel.add(cancelButton);
    }
    
    private void change3(String name) {
    	// 创建 JFrame 实例
        JFrame frame = new JFrame("设备"+name+"阈值修改");
        // Setting the width and height of frame
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);  //居中显示
        //只关闭此窗口，父窗口没影响
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents3(panel,frame,name);

        // 设置界面可见
        frame.setVisible(true);
    }
    private static void placeComponents3(JPanel panel,JFrame frame,String name) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel tempLabel = new JLabel("请输入温度阈值：");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        tempLabel.setBounds(50,20,160,25);
        panel.add(tempLabel);

        /* 
         * 创建文本域用于温度改变值输入
         */
        JTextField tempText = new JTextField(20);
        tempText.setBounds(180,20,80,25);
        panel.add(tempText);
        
        //温度的单位显示
        JLabel tempUnit = new JLabel("°C");
        tempUnit.setBounds(270,20,25,25);
        panel.add(tempUnit);

        
        JLabel humiLabel = new JLabel("请输入湿度阈值：");
        humiLabel.setBounds(50,50,160,25);
        panel.add(humiLabel);

        /* 
         * 创建文本域用于湿度改变值输入
         */
        JTextField humiText = new JTextField(20);
        humiText.setBounds(180,50,80,25);
        panel.add(humiText);

        //湿度单位显示
        JLabel humiUnit = new JLabel("%RH");
        humiUnit.setBounds(270,50,40,25);
        panel.add(humiUnit);
        
        JLabel pLabel = new JLabel("请输入污染阈值：");
        pLabel.setBounds(50,80,160,25);
        panel.add(pLabel);

        /* 
         * 创建文本域用于湿度改变值输入
         */
        JTextField pText = new JTextField(20);
        pText.setBounds(180,80,80,25);
        panel.add(pText);

        //湿度单位显示
        JLabel pUnit = new JLabel("（推荐值150）");
        pUnit.setBounds(270,80,100,25);
        panel.add(pUnit);
        
        // 创建确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setBounds(85, 120, 60, 25);
        confirmButton.addActionListener(new ActionListener() {
        	 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String t = tempText.getText();
				String h = humiText.getText();
				String p = pText.getText();
				
				try {
				    Static.t = Integer.parseInt(t);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				try {
				    Static.h = Integer.parseInt(h);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				try {
				    Static.p = Integer.parseInt(p);
				    Static.pollution = p;
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				
				Static.setchange(name);  //修改的值传到静态类中的 变量之后，通过次函数进行不同设备的t，h值的设置

                System.out.println("修改之后的t："+name+"**********"+Static.getT(name));
                System.out.println("修改之后的h："+name+"**********"+Static.getH(name));

                frame.dispose();
                changePollution.changeP();
            }
        });
        panel.add(confirmButton);
        
     // 创建取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(230, 120, 60, 25);
        cancelButton.addActionListener(new ActionListener() {
       	 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
        });
        panel.add(cancelButton);
    }
    
    private void change4(String name) {
    	// 创建 JFrame 实例
        JFrame frame = new JFrame("设备"+name+"阈值修改");
        // Setting the width and height of frame
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);  //居中显示
        //只关闭此窗口，父窗口没影响
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents4(panel,frame,name);

        // 设置界面可见
        frame.setVisible(true);
    }
    private static void placeComponents4(JPanel panel,JFrame frame,String name) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel tempLabel = new JLabel("请输入温度阈值：");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        tempLabel.setBounds(50,20,160,25);
        panel.add(tempLabel);

        /* 
         * 创建文本域用于温度改变值输入
         */
        JTextField tempText = new JTextField(20);
        tempText.setBounds(180,20,80,25);
        panel.add(tempText);
        
        //温度的单位显示
        JLabel tempUnit = new JLabel("°C");
        tempUnit.setBounds(270,20,25,25);
        panel.add(tempUnit);

        
        JLabel humiLabel = new JLabel("请输入湿度阈值：");
        humiLabel.setBounds(50,50,160,25);
        panel.add(humiLabel);

        /* 
         * 创建文本域用于湿度改变值输入
         */
        JTextField humiText = new JTextField(20);
        humiText.setBounds(180,50,80,25);
        panel.add(humiText);

        //湿度单位显示
        JLabel humiUnit = new JLabel("%RH");
        humiUnit.setBounds(270,50,40,25);
        panel.add(humiUnit);
        
        JLabel fLabel = new JLabel("请输入火光阈值：");
        fLabel.setBounds(50,80,160,25);
        panel.add(fLabel);

        /* 
         * 创建文本域用于湿度改变值输入
         */
        JTextField fText = new JTextField(20);
        fText.setBounds(180,80,80,25);
        panel.add(fText);

        //湿度单位显示
        JLabel fUnit = new JLabel("（推荐值800）");
        fUnit.setBounds(270,80,100,25);
        panel.add(fUnit);
        
        // 创建确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setBounds(85, 120, 60, 25);
        confirmButton.addActionListener(new ActionListener() {
        	 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String t = tempText.getText();
				String h = humiText.getText();
				String f = fText.getText();
				
				try {
				    Static.t = Integer.parseInt(t);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				try {
				    Static.h = Integer.parseInt(h);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				try {
				    Static.f = Integer.parseInt(f);
				    Static.fire = f;
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				
				Static.setchange(name);  //修改的值传到静态类中的 变量之后，通过次函数进行不同设备的t，h值的设置
				
				System.out.println("修改之后的t："+name+"**********"+Static.getT(name));
				System.out.println("修改之后的h："+name+"**********"+Static.getH(name));
				
				frame.dispose();
				changeFire.changeF();
			}
        });
        panel.add(confirmButton);
        
     // 创建取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(230, 120, 60, 25);
        cancelButton.addActionListener(new ActionListener() {
       	 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
        });
        panel.add(cancelButton);
    }
    
    Map getTempHashMap(){
        return Collections.unmodifiableMap(tempHashMap);
    }
    Map getHumiHashmap(){
        return Collections.unmodifiableMap(humiHashMap);
    }
    Map getVolHashMap(){
        return Collections.unmodifiableMap(volHashMap);
    }
    Map getLigHashMap(){
        return Collections.unmodifiableMap(lighashmap);
    }

}

public class Mgui {

    public static void main(String[] args) {

        //JFramDemo jf = JFramDemo.getInstance();
    }
}
