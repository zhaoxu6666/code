package fk;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import java.net.ServerSocket;
//import java.net.Socket;

class DataUpdate implements Runnable{
    List<Location> locations;
    DatagramPacket receivePacket;
    DataUpdate(DatagramPacket receivePacket, List<Location> locations){
        this.receivePacket = receivePacket;
        this.locations = locations;
    }
    @Override
    public void run(){
        //String connectedIp = socket.getInetAddress().getHostAddress();
		InetAddress connectedIp = receivePacket.getAddress();
        System.out.println(connectedIp + " connected.....");


        Location location = null;

        for(Location location1: locations){
            //System.out.println("ip:"+connectedIp+"ip");
	    //System.out.println("ip:"+location1.getIp()+"ip");
	    if(location1.getIp().equals(""+connectedIp)){               
		location = location1;
                break;
            }
        }
//        System.out.println(location.getName()+ " location connected thread￥￥￥￥");

        if(location == null) return;

        InputStream inputStream = null;
        //try {
        //    inputStream = socket.getInputStream();
        //}catch (IOException e){e.printStackTrace();return;}

	String sentence = new String(receivePacket.getData());
        //BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String name=location.getName();
        //try {
            //sensorId = Integer.parseInt(br.readLine());
		//一个子节点的所有传感器的数据

        switch (name) {
		case "1":
			setUp1(location,name,sentence);
//			System.out.println(Static.thread1+"1111111111111111111111111");
			if(Detect.thread1==""){
				thread(location,name);
			}else {
				Detect.i1 = 1;
			}
			break;
		case "2":
			setUp2(location,name,sentence);
//			System.out.println(Static.thread2+"22222222222222222222222");
			if(Detect.thread2==""){
				thread(location,name);
			}else {
				Detect.i2 = 1;
			}
			break;
		case "3":
			setUp3(location,name,sentence);
//			System.out.println(Static.thread3+"3333333333333333333");
			if(Detect.thread3==""){
				thread(location,name);
			}else {
				Detect.i3 = 1;
			}
			break;
		case "4":
			setUp4(location,name,sentence);
//			System.out.println(Static.thread4+"4444444444444444444");
			if(Detect.thread4==""){
				thread(location,name);
			}else {
				Detect.i4 = 1;
			}
			break;
		case "5":
			setUp1(location,name,sentence);
//			System.out.println(Static.thread5+"55555555555555555555");
			if(Detect.thread5==""){
				thread(location,name);
			}else {
				Detect.i5 = 1;
			}
			break;
		default:
			break;
		}
        //}catch (Exception e){e.printStackTrace(); return;}
		System.out.println("end");
        
    }
    //create thread Detect
    public void thread(Location location,String name){
			Detect t = new Detect(location,name);
			Thread t1 = new Thread(t);
			t1.start();
			Detect.setThread(name,t1.getName());
			System.out.println(t1.getName()+"&&&&&&&&&&&&&&&&&&&&&&&&"+name);
	}

	//create thread Flicker
	public void threadF(String name){
		Flicker t = new Flicker(name);
		Thread t1 = new Thread(t);
		t1.start();
		Flicker.setThreadF(name,t1.getName());
		System.out.println(t1.getName()+"*********************************"+name);
	}
	//判断阈值、设置显隐功能
    public void setUp1(Location location,String name,String sentence) {
		TH(location,name,sentence);
		V(location,sentence);
		L(location,sentence);
    }
    public void setUp2(Location location,String name,String sentence) {
		TH(location,name,sentence);
		L(location,sentence);
		String[] ss = sentence.split("\n");
		int sensorId3 = Integer.parseInt(ss[4]);
		String data3 = ss[5];
        if(sensorId3 == Sensors.VOLSENSOR.getId()){
        	if(Double.parseDouble(data3)<Static.a) {
        		location.getVolField().setForeground(Color.BLACK);
        		location.getVolField().setText(data3);
				Flicker.setSensorV(name,0);
        	}else {
        		location.getVolField().setForeground(Color.RED);
        		location.getVolField().setText(data3);
				if (Flicker.getThreadAttitude(name)==""){
					System.out.println("进入创建线程2222222222222");
					Flicker.setSensorV(name,1);
					threadF(name);  //create Flicker thread
				}
        	}
        }
    }
    public void setUp3(Location location,String name,String sentence) {
		TH(location,name,sentence);
		String[] ss = sentence.split("\n");
		int sensorId2 = Integer.parseInt(ss[4]);
		String data2 = ss[5];
		int sensorId3 = Integer.parseInt(ss[6]);
		String data3 = ss[7];
        if(sensorId2 == Sensors.VOLSENSOR.getId()){   //烟雾模块
        	if(Integer.parseInt(data2)<Static.p) {
        		location.getVolField().setForeground(Color.BLACK);
        		location.getVolField().setText(data2);
				Flicker.sensor3V = 0;
        	}else {
        		location.getVolField().setForeground(Color.RED);
        		location.getVolField().setText(data2);
				if (Flicker.threadF3==""){
					System.out.println("进入创建线程");
					Flicker.sensor3V = 1;
					threadF(name);  //create Flicker thread
				}
        	}
        }
        if(sensorId3 == Sensors.LIGSENSOR.getId()){   //震动模块
        	if(Integer.parseInt(data3)==1) {
        		data3 = "警告";
        		location.getLigField().setForeground(Color.RED);
        		location.getLigField().setText(data3);
        		if (Flicker.threadF3==""){
					System.out.println("进入创建线程");
					Flicker.sensor3L = 1;
					threadF(name);  //create Flicker thread
				}
			}else {
        		data3 = "正常";
        		location.getLigField().setForeground(Color.BLACK);
        		location.getLigField().setText(data3);
				Flicker.sensor3L = 0;
        	}
        }
    }
    public void setUp4(Location location,String name,String sentence) {
    	TH(location,name,sentence);
		V(location,sentence);
		String[] ss = sentence.split("\n");
		int sensorId3 = Integer.parseInt(ss[6]);
		String data3 = ss[7];
    	if(sensorId3 == Sensors.LIGSENSOR.getId()){  //火光模块
    		if(Integer.parseInt(data3)>Static.f) {
    			location.getLigField().setForeground(Color.RED);
    			location.getLigField().setText(data3);
				if (Flicker.getThreadAttitude(name)==""){
					System.out.println("进入创建线程");
					Flicker.setSensorL(name,1);
					threadF(name);  //create Flicker thread
				}
    		}else {
    			location.getLigField().setForeground(Color.BLACK);
    			location.getLigField().setText(data3);
				Flicker.confDisvisible(name);
    		}
    	}
    }

    //判断阈值、设置显隐功能
	public void TH(Location location,String name,String sentence) {
		String[] ss = sentence.split("\n");
		int sensorId = Integer.parseInt(ss[0]);
		String data = ss[1];
		int sensorId1 = Integer.parseInt(ss[2]);
		String data1 = ss[3];

		if (sensorId == Sensors.TEMPSENSOR.getId()) {
			if (Integer.parseInt(data) < Static.getT(name)) {
				location.getTempField().setForeground(Color.BLACK);
				location.getTempField().setText(data);
				Flicker.setSensorT(name,0);
			} else {
				location.getTempField().setForeground(Color.RED);
				location.getTempField().setText(data);
				if (Flicker.getThreadAttitude(name)==""){
					System.out.println("进入创建线程");
					Flicker.setSensorT(name,1);  //attitude conf
					threadF(name);  //create Flicker thread
				}
			}
		}
		if (sensorId1 == Sensors.HUMSENSOR.getId()) {
			if (Integer.parseInt(data1) < Static.getH(name)) {
				location.getHumField().setForeground(Color.BLACK);
				location.getHumField().setText(data1);
				Flicker.setSensorH(name,0);
			} else {
				location.getHumField().setForeground(Color.RED);
				location.getHumField().setText(data1);
				if (Flicker.getThreadAttitude(name)==""){
					System.out.println("进入创建线程");
					Flicker.setSensorH(name,1);
					threadF(name);  //create Flicker thread
				}
			}
		}
	}
	public void V(Location location,String sentence) {
		String[] ss = sentence.split("\n");
		int sensorId2 = Integer.parseInt(ss[4]);
		String data2 = ss[5];
		String name = location.getName();
		if(sensorId2 == Sensors.VOLSENSOR.getId()) {  //噪音模块
			if (Integer.parseInt(data2) == 0) {
				data2 = "正常";
				location.getVolField().setForeground(Color.BLACK);
				location.getVolField().setText(data2);
				Flicker.setSensorV(name,0);
			} else {
				data2 = "警告";
				location.getVolField().setForeground(Color.RED);
				location.getVolField().setText(data2);
				if (Flicker.getThreadAttitude(name)==""){
					System.out.println("进入创建线程");
					Flicker.setSensorV(name,1);  //attitude conf
					threadF(name);  //create Flicker thread
				}
			}
		}
	}
	public void L(Location location,String sentence) {
		String[] ss = sentence.split("\n");
		int sensorId3 = Integer.parseInt(ss[6]);
		String data3 = ss[7];
		String name = location.getName();
		if (sensorId3 == Sensors.LIGSENSOR.getId()) {
			if (Integer.parseInt(data3) == 0) {
				data3 = "警告";
				location.getLigField().setForeground(Color.RED);
				location.getLigField().setText(data3);
				System.out.println(Flicker.getThreadAttitude(name));
				if (Flicker.getThreadAttitude(name) == "") {
					System.out.println("进入创建线程2222222222llllllllll");
					Flicker.setSensorL(name, 1);  //attitude conf
					threadF(name);  //create Flicker thread
				}
			} else {
				data3 = "正常";
				location.getLigField().setForeground(Color.BLACK);
				location.getLigField().setText(data3);
				Flicker.setSensorL(name, 0);
			}
		}
	}
}


public class SensorServer {
    static List getSensors(Map<String, JComponent> tempHashMap, Map<String, JComponent> humHashMap,
                           Map<String, JComponent> volHashMap, Map<String, JComponent> ligHashMap) throws NullPointerException{
        List<Location> sensors = new ArrayList<>();
        JTextField tempField = null, humField = null, volField = null, ligField = null;
        for(Locations sensors1: Locations.values()){
            for(String key: tempHashMap.keySet()){
                //System.out.println(key);
                if(sensors1.getName().equals(key)){
                    //System.out.println("equal!");
                    tempField = (JTextField)tempHashMap.get(key);
                    humField = (JTextField)humHashMap.get(key);
                    volField = (JTextField)volHashMap.get(key);
                    ligField = (JTextField)ligHashMap.get(key);
                    break;
                }
            }
            if(tempField == null || humField == null || volField == null || ligField == null) throw
                    new NullPointerException("filed null! " + sensors1.getName());
            sensors.add(new Location(sensors1.getName(), sensors1.getLocation(), sensors1.getIp(),
                    tempField, humField, volField, ligField));
        }
        return Collections.unmodifiableList(sensors);
    }


    public static void main(String[] args) throws IOException {
        JFramDemo jf = JFramDemo.getInstance();

        Map<String, JComponent> tempHashmap = (Map<String, JComponent>)jf.getTempHashMap();
        Map<String, JComponent> humHashMap = (Map<String, JComponent>)jf.getHumiHashmap();
        Map<String, JComponent> volHashMap = (Map<String, JComponent>)jf.getVolHashMap();
        Map<String, JComponent> ligHashMap = (Map<String, JComponent>)jf.getLigHashMap();

        List<Location> locations = getSensors(tempHashmap, humHashMap, volHashMap, ligHashMap);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        DatagramSocket serverSocket1 = new DatagramSocket(1234);

        System.out.println("start...");

		while (true){

			byte[] receiveData = new byte[1024];

			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

			serverSocket1.receive(receivePacket);

			System.out.println("received UDP");

			executorService.execute(new DataUpdate(receivePacket, locations));  //异步处理
        }
    }
}
