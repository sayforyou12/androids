package com.android.card;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.Dimension;
import android.widget.GridLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Dudu extends JFrame implements WifiP2pManager.ActionListenerstener, Runnable {
	private static final long serialVersionUID = 1L;
	private JButton jbt[] = new JButton[12];
	private JButton start = new JButton("����");
	private JButton end = new JButton("����");
	private JLabel jlb = new JLabel("���� :  ");
	JTextArea  jta_jumsu;
	private JLabel time_jlb = new JLabel("�����ð� = 0:53");
	private BorderLayout bl = new BorderLayout(10, 10);
	private JPanel jp1 = new JPanel();
	private GridLayout gl1 = new GridLayout(3, 4);
	private JPanel jp2 = new JPanel();
	private GridLayout gl2 = new GridLayout(1, 2);
	private JPanel jp21 = new JPanel();
	private FlowLayout fl21 = new FlowLayout(FlowLayout.RIGHT);
	private int randomsu = 0;
	private int count = 0;
	ImageIcon background;
	ImageIcon dudugi = new ImageIcon("dudugi2.png");
	List<Integer> jumsuList = new ArrayList<Integer>();// ��������

	public void Play(String fileName)//������� �޼���
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.stop();
            clip.open(ais);
            clip.start();
         }
        catch (Exception ex)
        {
        }
        }

	
	
	public Dudu(String title) {
		super(title);
		this.init();// ���̾ƿ���ġ�� => 12��ư�� ��ü�� for������ ����
		this.start(); // ��ưŬ�� �̺�Ʈ�������ڵ鷯���� => 12��ư�� �׼Ǹ����� for������
		super.setSize(800, 600);
		// setBounds�̿��ϸ� ����
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true); // �ȵ���̵� Toast�� AlertDialog.Builder =>.show()
			
	}

	public void init() { // ���̾ƿ���ġ��
		Container con = this.getContentPane(); // Panel�� ���� ����
		con.setLayout(bl);
		con.add("North", time_jlb);
		con.add("Center", jp1);
		jp1.setLayout(gl1);
		for (int i = 0; i < 12; ++i) { // ��ư�� �迭�� ����
			jbt[i] = new JButton(background = new ImageIcon("background.png"));
			jp1.add(jbt[i]);
			setVisible(true);
		}
		off_button();
		con.add("South", jp2);
		jp2.setLayout(gl2);
		jp2.add(jlb);
		jp2.add(jp21);
		jp21.setLayout(fl21);
		jp21.add(start);
		jp21.add(end);
		jp2.setBackground(Color.green);
		jp21.setBackground(Color.green);
				
		//��������
		jta_jumsu = new JTextArea();
		JScrollPane jsp_east = new JScrollPane(jta_jumsu);
		jsp_east.setPreferredSize(new Dimension(100,10));
		this.add(jsp_east,"East");
		jta_jumsu.setEditable(false);
	}

	public void start() { // Non-static �׼Ǹ����ʿ��� xxx.addActionListener
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.addActionListener(this);
		end.addActionListener(this);
		for (int i = 0; i < 12; ++i) {

			jbt[i].addActionListener(this);
		}
	} // end

	public void actionPerformed(ActionEvent e) { // �׼ǰɱ�
		if (e.getSource() == start) {
			
			Play("bgm.wav");
			start.setEnabled(false);
			Thread th = new Thread(this); // Thread �ɱ�
			th.start(); // Thread ����
			on_button(); // ��ư on
			count = 0;
			//	random(0);
			dudu_show();
			
			
		} else if (e.getSource() == end) {
			System.exit(0);

		}
		for (int i = 0; i < 12; ++i) {
			if (e.getSource() == jbt[i]) {

				random(i);
				dudu_show();

			}
		}
	} // end

	public void off_button() {
		for (int i = 0; i < 12; ++i) {
			jbt[i].setEnabled(false);
		}
	} // end

	public void on_button() {
		for (int i = 0; i < 12; ++i) {
			jbt[i].setEnabled(true); // Ȱ��ȭ

		}
	} // end

	public void run() {
		int time = 53;
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			time--;
			if (time == 0) {
						
				time_jlb.setText("������ �������ϴ�.");
				start.setEnabled(true);
				jlb.setText("����:0");
  				jumsuList.add(count);
				
  				
				Integer [] rank_array = new Integer[jumsuList.size()];
				jumsuList.toArray(rank_array);
				Arrays.sort(rank_array);
				
				StringBuffer sb = new StringBuffer("----��������----\r\n");
				for (int i = rank_array.length-1; i>=0;i--) {
					sb.append(rank_array[i]);
					sb.append("\r\n");
				}
				
				jta_jumsu.setText(sb.toString());
				
				// count = 1;
				off_button();
				break;
			}
			time_jlb.setText("�����ð� = 0:" + time);
		}

	} // end
	public void random(int i) {
	    if (i != randomsu) {
			count--;
			jlb.setText("����: " + count);
			Play("wrong.wav");
			

	    } else {
			count++;
			jlb.setText("���� : " + count);
			Play("jump.wav");
		}

	} // end

	void dudu_show(){
		jbt[randomsu].setIcon(background);
		randomsu = (int) (Math.random() * 12);
		jbt[randomsu].setIcon(dudugi);
	}
	
	
	
	
	public static void main(String[] ar) {
		@SuppressWarnings("unused")
		Dudu ob = new Dudu("����");
	}

} // class end
