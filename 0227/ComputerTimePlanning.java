import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * Class <code>ComputerTimePlanning </code>
 * ʵ�ֶԿ��ü�����������޸�
 * @author   ����¶
 * @version v1.0@2013/8/13
 * @see      java.lang.Class
 * @since    JDK 1.6.0-31
 */
public class ComputerTimePlanning
{
	/** ����һ������*/
	private JFrame mainFrame;
	/** ����һ������*/
	private Container con;
	/**���塰ȫѡ����ť */
	private JButton jbtnSelectAll;
	/**���塰�˳�����ť */
	private JButton jbtnExit;
	/**���塰�ύ����ť */
	private JButton jbtnSubmit;
	/** ����һ�����鰴ť*/
	private JButton jbtn[] = new JButton[49];
	/**���塰������Ϣ�������� */
	private JComboBox jcmbComputer;
	/**����һ���ı��� */
	private JTextField jtxtCount;
	/** ���塱�ֵ���Ϣ��������*/
	private JComboBox jcmbPart;
	/**���塱�ܼ���������� ��ע*/
	private JLabel jlblTotal;
	/** �������ڱ�ע������*/
	private JLabel jlblWeek[]=new JLabel[7];
	/**����ʱ��α�ע������ */
	private JLabel jlblTime[]=new JLabel[7];
	/** ������ü����������ע*/
	private JLabel jlblUsable;
	/**���������Ϣ��ע */
	private JLabel jlblComputerRoom;
	/** ����ֵ���Ϣ��ע*/
	private JLabel jlblPart;
	/**��������ע */
	private JLabel jlblTopic;
	/**�������� */
	private Font normalFont;
	/** ���������ʼ��*/
	private String weekArray[]={"����һ","���ڶ�","������","������","������","������","������"};
	/** ʱ��������ʼ��*/
	private String timeArray[]={"08:00-10:00","10:00-12:00","12:00-14:00","14:00-16:00","16:00-18:00","18:00-20:00","20:00-22:00"};
	/**���ڼ�¼�����ť���±� */
	private int index;
	/**���ڼ�¼���ʱ��α�ע���±� */
	private int timeIndex;
	private int total;
	/** ���ڼ�¼������ڱ�����±�*/
	private int weekIndex;
	/** �������ֵ���¼��������޸ģ�1��ʾ���1����ť��2��ʾ���ڣ�3��ʾʱ�䣬4��ȫѡ*/
	private int click = 0;
	/**�����������ݿ���*/
	private Connection conn;
	/**����һ������� */
	private ResultSet rs;
	/**����һ����� */
	private java.sql.Statement statement;
	/**���ڷ�ֹ��������������Ϣ�¼� */
	private int flag;
	/**�����趨��ʼ��״̬*/
	private boolean SCAN = false;
	/** �����趨�޸�״̬*/
	private boolean MODIFY = true;
	/**
	 * 
	  * ���캯��
	  * @param       �ޡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	public ComputerTimePlanning()
	{
		builtFrame();
		
		initPart();

		dealAction();
	//	add();
	}
	
	/**
	 * 
	  * ����ϵͳ��ʾ�Ի���
	  * @param       �ַ������赯�������ݡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	private void showMessage(String mes)
	{
		JOptionPane.showConfirmDialog(mainFrame, mes, "ϵͳ��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
	}
	/**
	 * 
	  * ���ÿؼ����Ƿ���á�
	  * @param       true��false��true��ʾ�ύ���ã����ü���������༭�����á�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	public void setStatus(boolean value)
	{
		jtxtCount.setEnabled(value);
		jbtnSubmit.setEnabled(value);
		
	}
	
	
	/**
	 * 
	  * ���Լ�¼��һ�εĵĵ���¼�����ԭ��ť��ɫ 
	  * @param       �ޡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	private void restore()
	{
		for(int i = 0; i < 49; i++)
		{
			jbtn[i].setBackground(null);
			jbtn[i].setForeground(null);
		}
		for(int j = 0; j < 7; j++)
		{
			jlblTime[j].setForeground(null);
			jlblWeek[j].setForeground(null);
		}
	}
	
	/**
	 * 
	  * ��ʼ���ֵ���Ϣ��
	  * @param       �ޡ�
	  * @return      ��
	  * @exception   �ޡ�
	 */
	private void initPart()
	{
		 setStatus(SCAN);
		jcmbPart.removeAllItems();
	
		String SQLString = "SELECT * FROM SYS_EQUINOX_INFO ";
		SQLString+="WHERE USING_STATE=" + "'" + 1 + "'";
		connection();
		try {
			rs = statement.executeQuery(SQLString);
			while(rs.next())
			{
				String str =rs.getString("EQUINOX_ID") + " " + rs.getString("EQUINOX_NAME");
				jcmbPart.addItem(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnection();
		
		jcmbPart.setSelectedIndex(0);
		String str = jcmbPart.getSelectedItem().toString();
		
		if(!(str.equals(null)))
		
			initPractiseRoom(str.substring(0,2));
	}
	
	/**
	 * 
	  * ��ʼ��������Ϣ��
	  * @param       �ޡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
		private void initPractiseRoom(String equinoxId)
		{
			flag = 0;
			jcmbComputer.removeAllItems();			
			
			String SQLString = "SELECT * FROM SYS_PRACTISEROOM_INFO WHERE ";
			SQLString += "LEFT(PRACTISE_ROOM_ID,2)= " + "'" + equinoxId + "'";
			connection();
			try {
				rs=statement.executeQuery(SQLString);
				while(rs.next())
				{
					String str = rs.getString("PRACTISE_ROOM_ID") + " " + rs.getString("PRACTISE_ROOM_NAME");
					flag = 0 ;
					jcmbComputer.addItem(str);
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			disConnection();
			
			jcmbComputer.setSelectedIndex(0);
			if(jcmbComputer.getItemCount()>0)
			{
				initTotal(((String) jcmbComputer.getSelectedItem()).substring(0,4));
				initJbtn(((String) jcmbComputer.getSelectedItem()).substring(0,4));
				
			}
			flag=1;
		}

		/**
		 * 
		  *��ʼ����ť��������
		  * @param       �ޡ�
		  * @return      �ޡ�
		  * @exception   �ޡ�
		 */
		private void initJbtn(String practiseId)
		{
				
			String SQLString = "SELECT * FROM SYS_TIME_SECTION_INFO WHERE ";
			SQLString += "LEFT(COMPUTER_ROOM_ID,4) = " + "'" + practiseId + "'";
			connection();
			try {
				rs=statement.executeQuery(SQLString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next())
				{
						String str = rs.getString("TIME_SECTION_ID");
						int j = Integer.valueOf(str.substring(0,1));
						int i = Integer.valueOf(str.substring(1, 2));
						int index = (j-1)*7+(i-1);
						String str1 = rs.getString("USABLE_COUNT");
						
						jbtn[index].setText(str1);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			disConnection();
		}

		/**
		 * 
		  * ��ʼ���ܼ����������
		  * @param      �ޡ�
		  * @return     �ޡ�
		  * @exception  �ޡ�
		 */
		private void initTotal(String practiseId)
		{
			String SQLString = "SELECT * FROM SYS_PRACTISEROOM_INFO WHERE ";
			SQLString += "LEFT(PRACTISE_ROOM_ID,4) = " + "'" + practiseId + "'";
			connection();
			try {
				rs=statement.executeQuery(SQLString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next())
				{
					
					String str = "�ܼ��������" + rs.getString("AVAILABLE_COMPUTER") + "̨";
					
					jlblTotal.setText(str);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			disConnection();
			
		}

		/**
		 * 
		  * �������ݿ⺯����
		  * @param       �ޡ�
		  * @return      �ޡ�
		  * @exception   �ޡ�
		 */
		private void connection()
		{
					try {
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						conn = DriverManager.getConnection("jdbc:odbc:micro_code");
						statement = conn.createStatement();
					} catch (SQLException e)
					{
						System.out.println("JdbcOdbcDriver������");
						e.printStackTrace();
					} catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
				
		}

		/**
		 * 
		  * �Ͽ����ݿ⡣
		  * @param       �ޡ�
		  * @return      �ޡ�
		  * @exception   �ޡ�
		 */
		private void disConnection()
		{
			
				try {
					statement.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
		/**
		 * 
		  * �޸Ŀ��ü����������
		  * @param       �ޡ�
		  * @return      �ޡ�
		  * @exception   �ޡ�
		 */
	public void modify()
	{
		if(click == 1)
			modifyOneButton();
		else if(click == 2)
			modifyWeekButton();
		else if(click == 3)
			modifyTimeButton();
		else if(click == 4)
			modifyAllButton();
	}
	/**
	 * 
	  * �޸�һ���ڵ����п��ü����������
	  * @param       �ޡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	private void modifyAllButton() 
	{
		String setMessage = jtxtCount.getText().trim();
		String comId= ((String)jcmbComputer.getSelectedItem()).substring(0, 0 + 4);
		String SQLstring = "UPDATE SYS_TIME_SECTION_INFO SET USABLE_COUNT = " + setMessage + " WHERE COMPUTER_ROOM_ID = '" + comId + "'";
		connection();
		try {
			statement.execute(SQLstring);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disConnection();
	}
	/**
	 * 
	  * �޸�һ����ͬһʱ��Ŀ��ü����������
	  * @param       �ޡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	private void modifyTimeButton() 
	{
		int timeIndex = this.timeIndex;
		String setMessage = jtxtCount.getText().trim();
		String comId= ((String)jcmbComputer.getSelectedItem()).substring(0, 0 + 4);
		int index = ((timeIndex*7)/7 + 1) * 10 + (timeIndex*7) % 7 + 1;
		String SQLstring = "UPDATE SYS_TIME_SECTION_INFO SET USABLE_COUNT = " + setMessage + " WHERE TIME_SECTION_ID = '" + index + "'";
		int i = 1;
		for(timeIndex = timeIndex + 1;i < 7;i++)
		{		System.out.println(i);
			SQLstring += " OR TIME_SECTION_ID = '" + (index + i) + "'";
		}
		SQLstring +=" AND COMPUTER_ROOM_ID = '" + comId + "'";
		connection();
		
		try 
		{
			statement.execute(SQLstring);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		disConnection();
	}
	/**
	 * 
	  * �޸�ȫ��Ŀ��ü����������
	  * @param       �ޡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	private void modifyWeekButton()
	{
		int weekIndex;
		weekIndex=this.weekIndex;
		String setMessage = jtxtCount.getText().trim();
		String comId= ((String)jcmbComputer.getSelectedItem()).substring(0, 0 + 4);
		int index =10 + (weekIndex+1);
		String SQLstring = "UPDATE SYS_TIME_SECTION_INFO SET USABLE_COUNT = " + setMessage + " WHERE TIME_SECTION_ID = '" + index + "'";
		for(int i = 1;i<7;i++)
		{
			SQLstring += " OR TIME_SECTION_ID = '" + (index + 10*i) + "'";
		}
		SQLstring +=" AND COMPUTER_ROOM_ID = '" + comId + "'";
		connection();
		try {
			statement.execute(SQLstring);
		} catch (SQLException e)
		{ 
			e.printStackTrace();
		}
		disConnection();
	}
	/**
	 * 
	  * �޸�һ��ʱ��εĿ��ü����������
	  * @param       �ޡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	private void modifyOneButton()
	{
		int index;
		index = this.index;
		int indexAccess = (index/7 + 1) * 10 + index % 7 + 1;
		String comId= ((String)jcmbComputer.getSelectedItem()).substring(0, 0 + 4);
		String setMessage = jtxtCount.getText().trim();
		String SQLString ="UPDATE SYS_TIME_SECTION_INFO SET USABLE_COUNT = " + setMessage +
		" WHERE TIME_SECTION_ID = '" + indexAccess + "' AND COMPUTER_ROOM_ID = '" + comId + "'";
		
		connection();
		
		try 
		{
			statement.execute(SQLString);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		disConnection();
	}
	/**
	 * 
	  * ��֤�����Ƿ���Ч��
	  * @param       �ޡ�
	  * @return       ����true��false��true��ʾ������Ч��false��ʾ��Ч��
	  * @exception   �ޡ�
	 */
	private boolean check() 
	{
		boolean OK;
		OK = jtxtCount.getText().matches("^[0-9]|[1-9]\\d{1,2}$");
		return OK;
	}
	/**
	 * 
	  * �����¼���
	  * @param        �ޡ�
	  * @return       �ޡ�
	  * @exception    �ޡ�
	 */
	private void dealAction() 
	{
		
//�˳�����¼�
		jbtnExit.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					// TODO Auto-generated method stub
					restore();
					mainFrame.dispose();
				}
				
			}
		);
		
		jbtnSubmit.addActionListener
		(
			new ActionListener()
			{

				public void actionPerformed(ActionEvent arg0) 
				{
					total = Integer.valueOf(jlblTotal.getText().substring(jlblTotal.getText().indexOf("�ܼ��������")+6,jlblTotal.getText().indexOf("̨")));
					if(check()&&Integer.valueOf(jtxtCount.getText())<=total)
					{
						modify();
						restore();
						showMessage("�޸ĳɹ�");
						initJbtn(((String)jcmbComputer.getSelectedItem()).substring(0, 0 + 4));
						 setStatus(SCAN);
					}
					else
						showMessage("������Ϣ���Ϸ������޸�");
					
				}
			}
		);
		
	//����ֵ���Ϣ��������Ϣ��֮�ı�	
		
		jcmbPart.addActionListener
		(
			new ActionListener()
			{

				public void actionPerformed(ActionEvent arg0)
				{
					setStatus(SCAN);
					restore();
					String str = jcmbPart.getSelectedItem().toString();
					
					if(!(str.equals(null)))
					
						initPractiseRoom(str.substring(0,2));
				
				}
				
			}
		);
	//���������Ϣ�������Ͱ�ť��������֮�ı�	
	jcmbComputer.addActionListener
	(
			new ActionListener()
			{

				public void actionPerformed(ActionEvent e) 
				{
					setStatus(SCAN);
					restore();
					if(flag ==0)
						;
					
					else
					{
						if(jcmbComputer.getItemCount()>0)
						{
							initTotal(((String) jcmbComputer.getSelectedItem()).substring(0,4));
							initJbtn(((String) jcmbComputer.getSelectedItem()).substring(0,4));
							
						}						
					}
					
				}
			}
	);
//ȫѡ�¼�
	
		jbtnSelectAll.addActionListener
		(
			new ActionListener()
			{

				public void actionPerformed(ActionEvent arg0) 
				{
					// TODO Auto-generated method stub
					setStatus(MODIFY);
					click = 4;
					restore();
					jtxtCount.setText(jbtn[0].getText());
					jtxtCount.selectAll();
					jtxtCount.requestFocus();
					showMessage(jbtn[0].getText());
					for(int i = 0;i<49;i++)
					{
						if(jbtnSelectAll==arg0.getSource())
						{
							jbtn[i].setBackground(Color.blue);
							jbtn[i].setForeground(Color.white);
							
							if(!(jbtn[0].getText().equals(jbtn[i].getText())))
							{
								jtxtCount.setText("");
								jtxtCount.requestFocus();
							}
						}		
							
						else
						{
							jbtn[i].setForeground(jbtnExit.getForeground());
							jbtn[i].setBackground(jbtnExit.getBackground());
						}
						
					}
					
				}
				
			}
		);
		
		for (int i = 0;i<49;i++)
		{
			jbtn[i].addActionListener
			(	new ActionListener()
				{				
					public void actionPerformed(ActionEvent arg0) 
					{
						setStatus(MODIFY);
						click = 1;
						restore();
						String str = arg0.getSource().toString();
						index = Integer.valueOf(str.substring(str.indexOf("jbtn")+4,str.indexOf(",")));
						
						jbtn[index].setBackground(Color.blue);
						jbtn[index].setForeground(Color.white);
						jtxtCount.setText(jbtn[index].getText());
						jtxtCount.selectAll();
						jtxtCount.requestFocus();
						
					}
					
					
				}
			);
		}
	//�¼��ε���¼�
		for(int i = 0;i < 7;i ++)
		{
			jlblTime[i].addMouseListener
			(
					new MouseListener()
					{

						public void mouseClicked(MouseEvent arg0) 
						{
							setStatus(MODIFY);
							click = 3;
							restore();

							String str = arg0.getSource().toString();
							timeIndex = Integer.valueOf(str.substring(str.indexOf("time")+4,str.indexOf(",")) );
							
							jtxtCount.setText(jbtn[timeIndex*7].getText());
							jtxtCount.selectAll();
							jtxtCount.requestFocus();
							jlblTime[timeIndex].setForeground(Color.blue);
							for(int j = 0 ;j<49;j++)
							{
								for(int a=5;a<49;a+=7)
								if((timeIndex*7)<=j&&((timeIndex+1)*7)>j)
								{
									jbtn[j].setForeground(Color.white);
									jbtn[j].setBackground(Color.BLUE);
									
									if(!(jbtn[timeIndex].getText().equals(jbtn[j].getText())))
									{
										jtxtCount.setText("");
										jtxtCount.requestFocus();
									}
								}
							}
							
						}

						public void mouseEntered(MouseEvent arg0) 
						{
							
						}

						public void mouseExited(MouseEvent arg0) 
						{
							
						}

						public void mousePressed(MouseEvent arg0)
						{
							
						}

						public void mouseReleased(MouseEvent arg0) 
						{
							
						}
						
					}
			);
	
		}
		//���ڵ���¼�
		for(int i = 0;i < 7;i ++)
		{
			jlblWeek[i].addMouseListener
			(
					new MouseListener()
					{
						
						public void mouseClicked(MouseEvent arg0) 
						{
							setStatus(MODIFY);
							click = 2;
							restore();
							

							String str = arg0.getSource().toString();
							weekIndex = Integer.valueOf(str.substring(str.indexOf("week")+4,str.indexOf(",")) );
							
//							lastWeek=weekIndex;
							jtxtCount.setText(jbtn[weekIndex].getText());
							jtxtCount.selectAll();
							jtxtCount.requestFocus();
							jlblWeek[weekIndex].setForeground(Color.blue);
							
							for(int a=weekIndex;a<49;a+=7)
							{
								jbtn[a].setBackground(Color.blue);
								jbtn[a].setForeground(Color.white);
								
								if(!(jbtn[weekIndex].getText().equals(jbtn[a].getText())))
								{
									jtxtCount.setText("");
									jtxtCount.requestFocus();
								}

								if(!(jbtn[weekIndex].getText().equals(jbtn[a].getText())))
								{
									jtxtCount.setText("");
									jtxtCount.requestFocus();
								}
							}
							
						}

						public void mouseEntered(MouseEvent arg0) 
						{
							
						}

						public void mouseExited(MouseEvent arg0) 
						{
							
						}

						public void mousePressed(MouseEvent arg0)
						{
							
						}

						public void mouseReleased(MouseEvent arg0) 
						{
							
						}
						
					}
			);
	
		}
		
		
	}
	/**
	 * 
	  * �������档
	  * @param       �ޡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */

	public void builtFrame()
	{
		
		mainFrame = new JFrame("�ϻ�ʱ��滮");
		normalFont = new Font("����", Font.PLAIN, (int)15);
		mainFrame.setResizable(false);
		con = mainFrame.getContentPane();
		con.setLayout(null);
		mainFrame.setSize(529, 470);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocation((getScreenSize().width - mainFrame.getWidth())/2, (getScreenSize().height - mainFrame.getHeight())/2);
		mainFrame.setVisible(true);
		

		jbtnSelectAll = new JButton("ȫѡ");
		jbtnSelectAll.setBounds(248, 388, 73, 33);
		jbtnSelectAll.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jbtnSelectAll);
		jbtnSelectAll.setToolTipText("����޸�һ�ܵĿ��ü��������");
		jbtnSelectAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jbtnExit = new JButton("�˳�");
		jbtnExit.setBounds(432, 388, 73, 33);
		jbtnExit.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jbtnExit);
		jbtnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnExit.setToolTipText("����˳�����");

		jbtnSubmit = new JButton("�ύ");
		jbtnSubmit.setBounds(320, 388, 73, 33);
		jbtnSubmit.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jbtnSubmit);
		jbtnSubmit.setToolTipText("�������޸�");
		jbtnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jcmbComputer = new JComboBox();
		jcmbComputer.setBounds(336, 48, 169, 24);
		jcmbComputer.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jcmbComputer);
		jcmbComputer.setToolTipText("���ѡ�����");

		jtxtCount = new JTextField();
		jtxtCount.setBounds(136, 387, 41, 25);
		jtxtCount.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jtxtCount);
		jtxtCount.setToolTipText("�ڴ����뵱ǰ���ü��������(1-3λ����)");

		jcmbPart = new JComboBox();
		jcmbPart.setBounds(1200/15, 720/15, 2535/15, 360/15);
		jcmbPart.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jcmbPart);
		jcmbPart.setToolTipText("���ѡ��ֵ�");

		jlblTotal = new JLabel("��������� 0 ̨");
		jlblTotal.setBounds(2850/15, 1440/15, 2115/15, 240/15);
		jlblTotal.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jlblTotal);
		jlblUsable = new JLabel("���ü��������");
		jlblUsable.setBounds(16, 392, 112, 24);
		jlblUsable.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jlblUsable);

		jlblComputerRoom = new JLabel("������Ϣ");
		jlblComputerRoom.setBounds(272, 52, 64, 16);
		jlblComputerRoom.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jlblComputerRoom);

		jlblPart = new JLabel("�ֵ���Ϣ");
		jlblPart.setBounds(240/15, 780/15, 64, 16);
		jlblPart.setFont(new Font("����", Font.PLAIN, (int)15));
		con.add(jlblPart);

		jlblTopic = new JLabel("�ϻ�ʱ��滮");
		jlblTopic.setBounds(170, 0, 180, 29);
		jlblTopic.setFont(new Font("����", Font.PLAIN, (int)24));
		jlblTopic.setForeground(new Color(0x000000FF));
		con.add(jlblTopic);

		for(int i = 0;i < 7;i++)
		{
			
			jlblWeek[i]=new JLabel(weekArray[i]);//ע�⣡������
			jlblWeek[i].setFont(normalFont);
			jlblWeek[i].setBounds(116+56*i, 120, 48, 16);
			jlblWeek[i].setName("week"+i);
			mainFrame.add(jlblWeek[i]);
		}
		for(int i = 0;i < 7;i++)
		{
			jlblTime[i]=new JLabel(timeArray[i]);//ע�⣡������
			jlblTime[i].setFont(normalFont);
			jlblTime[i].setBounds(16, 152+32*i,88 ,16 );
			jlblTime[i].setName("time"+i);
			mainFrame.add(jlblTime[i]);
		}
		for(int i = 0;i<49;i++)
		{
			int x = i%7,y = i/7;
			
				jbtn[i] = new JButton();
				jbtn[i].setFont(new Font("����", Font.PLAIN, (int)14));
				jbtn[i].setBounds(112+56*x, 144+32*y, 57, 33);
				jbtn[i].setText("80");
				jbtn[i].setName("jbtn"+i);
				mainFrame.add(jbtn[i]);
		}
		for(int i = 0 ;i<49;i++)
		{
			jbtn[i].setToolTipText("����޸�ĳ��ʱ��εĿ��ü��������");
			jbtn[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		for(int i = 0;i<7;i++)
		{
			jlblWeek[i].setToolTipText("����޸�һ��Ŀ��ü��������");
			jlblTime[i].setToolTipText("����޸�һ��ͬһʱ��εĿ��ü��������");
		}
	}
	/**
	 * 
	  * �����ݿ�������ݡ�
	  * @param       �ޡ�
	  * @return       �ޡ�
	  * @exception    �ޡ�
	 */
	public void add()
	{
//		String comId= ((String)jcmbComputer.getSelectedItem()).substring(0, 0 + 4);
		//String SQLString = "insert into SYS_TIME_SECTION_INFO (TIME_SECTION_ID��USABLE_COUNT,COMPUTER_ROOM_ID) ";
		for(int i = 0 ;i<49;i++)
		{
			String SQLString = "insert into SYS_TIME_SECTION_INFO (TIME_SECTION_ID,USABLE_COUNT,COMPUTER_ROOM_ID) ";
			String SectionTimeId = String.valueOf(i/7+1) + String.valueOf(i%7+1);
			SQLString += "VALUES ("+ "'"+SectionTimeId+"'" + "," + "'0'"+ ","+"'0002'"+")";
			System.out.println(SQLString);
//			SQLString += ") WHERE COMPUTER_ROOM_ID = " + "'"+comId + "'";
			connection();
			try {
				statement.execute(SQLString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			disConnection();
			SQLString= "";
		}
		
	}
	/**
	 * 
	  *	 ������С�
	  * @param       �ޡ�
	  * @return       �ޡ�
	  * @exception    �ޡ�
	 */
	public Dimension getScreenSize()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	/**
	 * 
	  * 		������
	  * @param      �ַ���
	  * @return      ��
	  * @exception   ��
	 */
	public static void main(String[] args)
	{
		new  ComputerTimePlanning();
	}
	
}
