package pf;

import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import pf.FileUtils;
public class image {
	public static void main(String[] args) {
		InitGlobalFont(new Font("KF-GB Gothic",Font.PLAIN,15));
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
			//BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
			
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("set skin fail!");
		}
		WZframe framw = new WZframe();
	}
	
private static void InitGlobalFont(Font font) {
FontUIResource fontRes = new FontUIResource(font);
for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
	Object key = keys.nextElement();
	Object value = UIManager.get(key);
	if (value instanceof FontUIResource) {
		UIManager.put(key, fontRes);
	}
}
}
}
class WZframe extends JFrame {
	private int fNum=0;
	private List<File> fList = null;
	private JTextArea textfield; // �ı����
	private JPanel TextPanel; // �ı���
	private JScrollPane jsp;
	private File file;
	private FileDialog openDia;
    private JFileChooser dirDia;
	JRadioButton box1; // ��ѡ��
	JRadioButton box2; // ��ѡ��
	JPanel radioPane; // ��ѡ�����
	//private JLabel label2;

	JSlider slider; // ����
	JPanel sliderPanel;// �������

	JComboBox<Object> comboBox; // �ı�ѡ���
	JPanel ComboBoxPane;

	JButton spinner;
	JButton lastPage;
	JButton nextPage;
	// JSpinner spinner;//΢�����
	JPanel spinerPane;// ΢��������

	JMenuBar bar; // �˵���
	JMenu FileMenu;// �ļ��˵�����
	JMenu SetMenu; // ���ò˵�����
	JMenu ViewMenu;// ��ͼ�˵�����
	JMenu HelpMenu; // �����˵�
	//private ImageIcon asdIcon;

	public WZframe() {
		this.setResizable(false);
		setSize(800, 800);
		setTitle("��Ϣ����");
		// ��ʼ���������

		openDia = new FileDialog(new JFrame(), "��", FileDialog.LOAD);
		dirDia = new JFileChooser();
		dirDia.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//ֻ��ѡ��Ŀ¼
		TextPanel = new JPanel();
		textfield = new JTextArea();
		jsp= new JScrollPane(textfield);
		Toolkit kit = Toolkit.getDefaultToolkit();
		//asdIcon = new ImageIcon("data/xgboost/212.png"); // ������Ҫ��ʾ��ͼƬ
		/*****************************************/

		// ����Ļ�������

		/* 1.�ı��� */
		TextPanel.setBounds(10, 10, getWidth() - 40, getHeight() / 3 * 2 + 100);
		TextPanel.setLayout(null);
		jsp.setBounds(10, 25, TextPanel.getWidth() - 20, TextPanel.getHeight() - 40);
		TextPanel.add(jsp);
		Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "�ı�������");
		TextPanel.setBorder(border);
		textfield.setForeground(Color.white);
		textfield.setBackground(Color.black);
		textfield.setLineWrap(true);
		textfield.setFont(new Font("SansSerif", Font.PLAIN, 25));

		/****************************************/

		// �����ǵ�ѡ������
		RadioListener radioListener = new RadioListener();
		ButtonGroup group = new ButtonGroup();
		box1 = new JRadioButton("��ɫ");
		box2 = new JRadioButton("ůɫ");
		group.add(box1);
		group.add(box2);
		box1.addActionListener(radioListener);
		box2.addActionListener(radioListener);
		box1.setBounds(30, 35, 70, 15);
		box2.setBounds(100, 35, 80, 15);
		radioPane = new JPanel();
		radioPane.setLayout(null);
		radioPane.setBounds(10, 650, 200, 80);
		radioPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ɫ��ѡ��"));
		radioPane.add(box1);
		radioPane.add(box2);

		/****************************************/

		// �����Сѡ��
		sliderPanel = new JPanel();
		slider = new JSlider(10, 100, 25);
		slider.setBounds(10, 30, 200, 40);
		sliderPanel.setLayout(null);
		sliderPanel.add(slider);
		sliderPanel.setBounds(220, 650, 230, 80);
		sliderPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "��������"));
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.addChangeListener(new SlideListener());

		/*****************************************/

		// ����ѡ��
		comboBox = new JComboBox<Object>();
		ComboBoxPane = new JPanel();
		ComboBoxPane.setBounds(460, 650, 180, 80);
		ComboBoxPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "����ѡ��"));
		ComboBoxPane.add(comboBox);
		ComboBoxPane.setLayout(null);
		comboBox.setBounds(30, 35, 80, 20);
		comboBox.addItem("ITALIC");
		comboBox.addItem("PLAIN");
		comboBox.addActionListener(new ComboBoxListener());
		comboBox.setEditable(false);

		/********************************************/

		// ΢��������
		spinerPane = new JPanel();
		spinerPane.setLayout(null);
		spinerPane.setBounds(650, 650, 120, 80);
		spinerPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "��������"));
		// spinner=new JSpinner(new SpinnerNumberModel(20, 10, 100, 5));
		// Integer[] nub={5,8,5,4,5,5,8,4};
		// String[] str= {"a","b","c","d"};
		spinner = new JButton("start");
		spinner.setBounds(15, 25, 80, 40);
		lastPage = new JButton("last");
		lastPage.setBounds(5,25,50,40);
		nextPage = new JButton("next");
		nextPage.setBounds(60, 25, 50, 40);
		lastPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				if(fNum>1)
				{
					textfield.setText("");
					fNum--;
					file = fList.get(fNum-1);
					try {
					BufferedReader bufferedReader = 
							new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
					String line = null;
					int fl=0;
					while ((line = bufferedReader.readLine()) != null) {
						if(!line.contains("detailed_description")&&fl==0)
						{
							if(line.contains("brief_title"))
								textfield.append(line + "\r\n");
							else 
							{
							if(line.length()>=170)	
								textfield.append(line.substring(0,170)+".etc" + "\r\n");
							else
								textfield.append(line + "\r\n");
							}
							}	
						else fl=1;
						if(line.equals(""))
						{
							fl=0;
							textfield.append("\r\n");
						}
					}
					bufferedReader.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				textfield.setCaretPosition(1);
			}
		});
		nextPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				if(fNum<fList.size())
				{
					textfield.setText("");
					file = fList.get(fNum);
					fNum++;
					try {
						BufferedReader bufferedReader = 
								new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
					String line = null;
					int fl=0;
					while ((line = bufferedReader.readLine()) != null) {
						if(!line.contains("detailed_description")&&fl==0)
						{
							if(line.contains("brief_title"))
								textfield.append(line + "\r\n");
							else 
								{
								if(line.length()>=170)	
									textfield.append(line.substring(0,170)+".etc" + "\r\n");
								else
									textfield.append(line + "\r\n");
								}
								}					
						else fl=1;
						if(line.equals(""))
						{
							fl=0;
							textfield.append("\r\n");
						}
					}
					bufferedReader.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				textfield.setCaretPosition(1);
			}
		});
		spinner.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dirDia.showDialog(new JLabel(), "ѡ��");  
				String dirPath = dirDia.getSelectedFile().toString();
				if (dirPath == null) {
					return;
				}
				System.out.println(dirPath);
				fList = FileUtils.getFileSort(dirPath);
				textfield.setText("");
				
				if(fNum < fList.size())
				{
					file = fList.get(fNum);
					fNum++;
					try {
						BufferedReader bufferedReader = 
								new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
					String line = null;
					int fl=0;
					while ((line = bufferedReader.readLine()) != null) {
						if(!line.contains("detailed_description")&&fl==0)
						{
							if(line.contains("brief_title"))
								textfield.append(line + "\r\n");
							else 
							{
							if(line.length()>=170)	
								textfield.append(line.substring(0,170)+".etc" + "\r\n");
							else
								textfield.append(line + "\r\n");
							}
							}						
						else fl=1;
						if(line.equals(""))
						{
							fl=0;
							textfield.append("\r\n");
						}
					}
					bufferedReader.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				textfield.setCaretPosition(1);
				//����ť���
				spinerPane.remove(spinner);
				spinerPane.add(lastPage);
				spinerPane.add(nextPage);
				spinerPane.updateUI();
				spinerPane.repaint();
			}
		});

		/*
		 * ���ָ�ʽ��:SpinnerNuberModel �б��ʽ��:SpinnerListModel ���ڸ�ʽ��:SpinnerDateMode
		 */

		spinerPane.add(spinner);

		/*******************************************/

		// ���Ӳ˵�
		bar = new JMenuBar();
		HelpMenu = new JMenu("����");

		JMenuItem HelpItem = new JMenuItem("˵��", 84);
		HelpItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				System.out.println("text");
			}
		});
		HelpItem.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		HelpMenu.add(HelpItem);

		FileMenu = new JMenu("�ļ�");
		JMenuItem OtherSava = FileMenu.add("��");
		OtherSava.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		FileMenu.addSeparator(); // ��Ч����ʹ��˳���йأ�
//		JMenuItem savaItem = FileMenu.add("ѵ��");
//		// savaItem.setIcon(new ImageIcon("F:\\ͼƬ\\3.png"));
//		savaItem.setHorizontalTextPosition(SwingConstants.LEFT);
//		OtherSava.setHorizontalTextPosition(SwingConstants.LEFT);
//		// OtherSava.setIcon(new ImageIcon("F:\\ͼƬ\\3.png"));
//		savaItem.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//System.out.println("��ʼѵ��!");
//				JOptionPane.showMessageDialog(null, "����ѵ������ȴ�", "ѵ����", JOptionPane.INFORMATION_MESSAGE);
//				try {
//					Process proc = Runtime.getRuntime().exec("java -jar -Dfile.encoding=UTF-8 lib/LDAModel.jar");
//					proc.waitFor();
//				} catch (IOException | InterruptedException e1) {
//					// TODO �Զ����ɵ� catch ��
//					e1.printStackTrace();
//				}
//				JOptionPane.showMessageDialog(null, "ѵ�����", "ѵ�����", JOptionPane.CLOSED_OPTION);
//			}
//		});

		OtherSava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openDia.setVisible(true);
				String dirPath = openDia.getDirectory();
				String fileName = openDia.getFile();
				if (dirPath == null || fileName == null) {
					return;
				}
				textfield.setText("");
				file = new File(dirPath, fileName);
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						textfield.append(line + "\r\n");
					}
					bufferedReader.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JMenuItem exie = FileMenu.add(new action("�˳�"));
		exie.setHorizontalTextPosition(SwingConstants.LEFT);
		// exie.setEnabled(false);
		// JMenuItem SavaAndExit=FileMenu.add(new action("���沢�˳�"));

		SetMenu = new JMenu("����");
		JMenuItem FontMenu = SetMenu.add("����");

		FontMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				System.out.println("��������ɹ�!");

			}
		});

		bar.add(FileMenu);
		bar.add(SetMenu);
		// bar.add(ViewMenu);
		bar.add(HelpMenu);
		this.setJMenuBar(bar);

		/*******************************************/

		// ��ܻ����ĳ�ʼ��
		Dimension dim = kit.getScreenSize();
		Container con = getContentPane();
		setLocation((int) dim.getWidth() / 3, (int) dim.getHeight() / 10);
		con.setLayout(null);
		con.add(TextPanel);
		con.add(radioPane);
		con.add(sliderPanel);
		con.add(ComboBoxPane);
		con.add(spinerPane);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*******************************************/

	}

	public class ShortCutKeyAction extends AbstractAction {
		public ShortCutKeyAction(String name, String key) {
			putValue(NAME, name);
			putValue(MNEMONIC_KEY, 84);

		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("����Ժܺõ�ʹ�øñ༭��");
		}
	}

	public class action extends AbstractAction {
		public action(String name) {
			putValue(action.NAME, name);
			putValue(action.SMALL_ICON, new ImageIcon("F:\\ͼƬ\\3.png"));
			System.out.println(Action.SMALL_ICON);

		}

		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			System.out.println(item.getText());
			if (item.getText().equals("�˳�")) {
				System.exit(0);
				System.out.println("�ɹ��˳�");
			} else if (item.getText().equals("���沢�˳�")) {
				System.exit(0);
				System.out.println("�ɹ����沢�˳�");

			}

		}

	}

	public class ColorFontListener extends AbstractAction {
		public ColorFontListener(Color color, String name) {
			putValue(NAME, name);
			putValue("Color", color);

		}

		public void actionPerformed(ActionEvent e) {
			textfield.setForeground((Color) getValue("Color"));
		}

	}

	public class ComboBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			JComboBox<Object> box = (JComboBox<Object>) arg0.getSource();
			if (box.getSelectedIndex() == 0) {
				textfield.setFont(new Font("SansSerif", Font.ITALIC, slider.getValue()));
			}
			if (box.getSelectedIndex() == 1) {
				System.out.println("aaaa");
				textfield.setFont(new Font("SansSerif", Font.PLAIN, slider.getValue()));
			}

		}

	}

	public class SlideListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();

			textfield.setFont(new Font("SansSerif", 0, slider.getValue()));
		}

	}

	public class RadioListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (box1.isSelected()) {
				textfield.setBackground(Color.blue);
				textfield.setForeground(Color.white);
			}

			if (box2.isSelected()) {
				textfield.setBackground(Color.YELLOW);
				textfield.setForeground(Color.RED);
			}

		}

	}

}
