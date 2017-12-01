package mymain;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Dudu extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	private JButton jbt[] = new JButton[12];
	private JButton start = new JButton("시작");
	private JButton end = new JButton("종료");
	private JLabel jlb = new JLabel("점수 :  ");
	JTextArea  jta_jumsu;
	private JLabel time_jlb = new JLabel("남은시간 = 0:53");
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
	List<Integer> jumsuList = new ArrayList<Integer>();// 점수저장

	public void Play(String fileName)//음악재생 메서드
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
		this.init();// 레이아웃배치도 => 12버튼의 개체를 for문에서 생성
		this.start(); // 버튼클릭 이벤트리스너핸들러연결 => 12버튼에 액션리스너 for문연결
		super.setSize(800, 600);
		// setBounds이용하면 편함
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true); // 안드로이드 Toast나 AlertDialog.Builder =>.show()
			
	}

	public void init() { // 레이아웃배치도
		Container con = this.getContentPane(); // Panel과 같은 역할
		con.setLayout(bl);
		con.add("North", time_jlb);
		con.add("Center", jp1);
		jp1.setLayout(gl1);
		for (int i = 0; i < 12; ++i) { // 버튼을 배열로 만듦
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
				
		//점수보기
		jta_jumsu = new JTextArea();
		JScrollPane jsp_east = new JScrollPane(jta_jumsu);
		jsp_east.setPreferredSize(new Dimension(100,10));
		this.add(jsp_east,"East");
		jta_jumsu.setEditable(false);
	}

	public void start() { // Non-static 액션리스너연결 xxx.addActionListener
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.addActionListener(this);
		end.addActionListener(this);
		for (int i = 0; i < 12; ++i) {

			jbt[i].addActionListener(this);
		}
	} // end

	public void actionPerformed(ActionEvent e) { // 액션걸기
		if (e.getSource() == start) {
			
			Play("bgm.wav");
			start.setEnabled(false);
			Thread th = new Thread(this); // Thread 걸기
			th.start(); // Thread 시작
			on_button(); // 버튼 on
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
			jbt[i].setEnabled(true); // 활성화

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
						
				time_jlb.setText("게임이 끝났습니다.");
				start.setEnabled(true);
				jlb.setText("점수:0");
  				jumsuList.add(count);
				
  				
				Integer [] rank_array = new Integer[jumsuList.size()];
				jumsuList.toArray(rank_array);
				Arrays.sort(rank_array);
				
				StringBuffer sb = new StringBuffer("----점수보기----\r\n");
				for (int i = rank_array.length-1; i>=0;i--) {
					sb.append(rank_array[i]);
					sb.append("\r\n");
				}
				
				jta_jumsu.setText(sb.toString());
				
				// count = 1;
				off_button();
				break;
			}
			time_jlb.setText("남은시간 = 0:" + time);
		}

	} // end
	public void random(int i) {
	    if (i != randomsu) {
			count--;
			jlb.setText("점수: " + count);
			Play("wrong.wav");
			

	    } else {
			count++;
			jlb.setText("점수 : " + count);
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
		Dudu ob = new Dudu("게임");
	}

} // class end
