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
 * 实现对可用计算机数量的修改
 * @author   黄燕露
 * @version v1.0@2013/8/13
 * @see      java.lang.Class
 * @since    JDK 1.6.0-31
 */
public class ComputerTimePlanning
{
	/** 定义一个窗口*/
	private JFrame mainFrame;
	/** 定义一个容器*/
	private Container con;
	/**定义“全选”按钮 */
	private JButton jbtnSelectAll;
	/**定义“退出”按钮 */
	private JButton jbtnExit;
	/**定义“提交”按钮 */
	private JButton jbtnSubmit;
	/** 定义一个数组按钮*/
	private JButton jbtn[] = new JButton[49];
	/**定义“机房信息“下拉框 */
	private JComboBox jcmbComputer;
	/**定义一个文本框 */
	private JTextField jtxtCount;
	/** 定义”分点信息“下拉框*/
	private JComboBox jcmbPart;
	/**定义”总计算机数量“ 标注*/
	private JLabel jlblTotal;
	/** 定义星期标注的数组*/
	private JLabel jlblWeek[]=new JLabel[7];
	/**定义时间段标注的数组 */
	private JLabel jlblTime[]=new JLabel[7];
	/** 定义可用计算机数量标注*/
	private JLabel jlblUsable;
	/**定义机房信息标注 */
	private JLabel jlblComputerRoom;
	/** 定义分点信息标注*/
	private JLabel jlblPart;
	/**定义标题标注 */
	private JLabel jlblTopic;
	/**定义字体 */
	private Font normalFont;
	/** 星期数组初始化*/
	private String weekArray[]={"星期一","星期二","星期三","星期四","星期五","星期六","星期天"};
	/** 时间段数组初始化*/
	private String timeArray[]={"08:00-10:00","10:00-12:00","12:00-14:00","14:00-16:00","16:00-18:00","18:00-20:00","20:00-22:00"};
	/**用于记录点击按钮的下标 */
	private int index;
	/**用于记录点击时间段标注的下标 */
	private int timeIndex;
	private int total;
	/** 用于记录点击星期表组的下标*/
	private int weekIndex;
	/** 用于区分点击事件，方便修改，1表示点击1个按钮，2表示星期，3表示时间，4表全选*/
	private int click = 0;
	/**定义链接数据库类*/
	private Connection conn;
	/**定义一个结果集 */
	private ResultSet rs;
	/**定义一个借口 */
	private java.sql.Statement statement;
	/**用于防止触发监听机房信息事件 */
	private int flag;
	/**用于设定初始化状态*/
	private boolean SCAN = false;
	/** 用于设定修改状态*/
	private boolean MODIFY = true;
	/**
	 * 
	  * 构造函数
	  * @param       无。
	  * @return      无。
	  * @exception   无。
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
	  * 弹出系统提示对话框
	  * @param       字符串，需弹出的内容。
	  * @return      无。
	  * @exception   无。
	 */
	private void showMessage(String mes)
	{
		JOptionPane.showConfirmDialog(mainFrame, mes, "系统温馨提示", JOptionPane.OK_CANCEL_OPTION);
	}
	/**
	 * 
	  * 设置控件的是否可用。
	  * @param       true或false，true表示提交可用，可用计算机数量编辑区可用。
	  * @return      无。
	  * @exception   无。
	 */
	public void setStatus(boolean value)
	{
		jtxtCount.setEnabled(value);
		jbtnSubmit.setEnabled(value);
		
	}
	
	
	/**
	 * 
	  * 用以记录上一次的的点击事件，还原按钮颜色 
	  * @param       无。
	  * @return      无。
	  * @exception   无。
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
	  * 初始化分点信息。
	  * @param       无。
	  * @return      无
	  * @exception   无。
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
	  * 初始化机房信息。
	  * @param       无。
	  * @return      无。
	  * @exception   无。
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
		  *初始化按钮的数量。
		  * @param       无。
		  * @return      无。
		  * @exception   无。
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
		  * 初始化总计算机数量。
		  * @param      无。
		  * @return     无。
		  * @exception  无。
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
					
					String str = "总计算机总量" + rs.getString("AVAILABLE_COMPUTER") + "台";
					
					jlblTotal.setText(str);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			disConnection();
			
		}

		/**
		 * 
		  * 链接数据库函数。
		  * @param       无。
		  * @return      无。
		  * @exception   无。
		 */
		private void connection()
		{
					try {
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						conn = DriverManager.getConnection("jdbc:odbc:micro_code");
						statement = conn.createStatement();
					} catch (SQLException e)
					{
						System.out.println("JdbcOdbcDriver不存在");
						e.printStackTrace();
					} catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
				
		}

		/**
		 * 
		  * 断开数据库。
		  * @param       无。
		  * @return      无。
		  * @exception   无。
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
		  * 修改可用计算机数量。
		  * @param       无。
		  * @return      无。
		  * @exception   无。
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
	  * 修改一周内的所有可用计算机数量。
	  * @param       无。
	  * @return      无。
	  * @exception   无。
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
	  * 修改一周内同一时间的可用计算机数量。
	  * @param       无。
	  * @return      无。
	  * @exception   无。
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
	  * 修改全天的可用计算机数量。
	  * @param       无。
	  * @return      无。
	  * @exception   无。
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
	  * 修改一个时间段的可用计算机数量。
	  * @param       无。
	  * @return      无。
	  * @exception   无。
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
	  * 验证输入是否有效。
	  * @param       无。
	  * @return       返回true或false，true表示输入有效，false表示无效。
	  * @exception   无。
	 */
	private boolean check() 
	{
		boolean OK;
		OK = jtxtCount.getText().matches("^[0-9]|[1-9]\\d{1,2}$");
		return OK;
	}
	/**
	 * 
	  * 监听事件。
	  * @param        无。
	  * @return       无。
	  * @exception    无。
	 */
	private void dealAction() 
	{
		
//退出点击事件
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
					total = Integer.valueOf(jlblTotal.getText().substring(jlblTotal.getText().indexOf("总计算机总量")+6,jlblTotal.getText().indexOf("台")));
					if(check()&&Integer.valueOf(jtxtCount.getText())<=total)
					{
						modify();
						restore();
						showMessage("修改成功");
						initJbtn(((String)jcmbComputer.getSelectedItem()).substring(0, 0 + 4));
						 setStatus(SCAN);
					}
					else
						showMessage("输入信息不合法，请修改");
					
				}
			}
		);
		
	//点击分点信息，机房信息随之改变	
		
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
	//点击机房信息，总数和按钮的数字随之改变	
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
//全选事件
	
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
	//事件段点击事件
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
		//星期点击事件
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
	  * 创建界面。
	  * @param       无。
	  * @return      无。
	  * @exception   无。
	 */

	public void builtFrame()
	{
		
		mainFrame = new JFrame("上机时间规划");
		normalFont = new Font("宋体", Font.PLAIN, (int)15);
		mainFrame.setResizable(false);
		con = mainFrame.getContentPane();
		con.setLayout(null);
		mainFrame.setSize(529, 470);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocation((getScreenSize().width - mainFrame.getWidth())/2, (getScreenSize().height - mainFrame.getHeight())/2);
		mainFrame.setVisible(true);
		

		jbtnSelectAll = new JButton("全选");
		jbtnSelectAll.setBounds(248, 388, 73, 33);
		jbtnSelectAll.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jbtnSelectAll);
		jbtnSelectAll.setToolTipText("点击修改一周的可用计算机数量");
		jbtnSelectAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jbtnExit = new JButton("退出");
		jbtnExit.setBounds(432, 388, 73, 33);
		jbtnExit.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jbtnExit);
		jbtnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnExit.setToolTipText("点击退出界面");

		jbtnSubmit = new JButton("提交");
		jbtnSubmit.setBounds(320, 388, 73, 33);
		jbtnSubmit.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jbtnSubmit);
		jbtnSubmit.setToolTipText("点击完成修改");
		jbtnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jcmbComputer = new JComboBox();
		jcmbComputer.setBounds(336, 48, 169, 24);
		jcmbComputer.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jcmbComputer);
		jcmbComputer.setToolTipText("点击选择机房");

		jtxtCount = new JTextField();
		jtxtCount.setBounds(136, 387, 41, 25);
		jtxtCount.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jtxtCount);
		jtxtCount.setToolTipText("在此输入当前可用计算机数量(1-3位数字)");

		jcmbPart = new JComboBox();
		jcmbPart.setBounds(1200/15, 720/15, 2535/15, 360/15);
		jcmbPart.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jcmbPart);
		jcmbPart.setToolTipText("点击选择分点");

		jlblTotal = new JLabel("计算机总数 0 台");
		jlblTotal.setBounds(2850/15, 1440/15, 2115/15, 240/15);
		jlblTotal.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jlblTotal);
		jlblUsable = new JLabel("可用计算机数量");
		jlblUsable.setBounds(16, 392, 112, 24);
		jlblUsable.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jlblUsable);

		jlblComputerRoom = new JLabel("机房信息");
		jlblComputerRoom.setBounds(272, 52, 64, 16);
		jlblComputerRoom.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jlblComputerRoom);

		jlblPart = new JLabel("分点信息");
		jlblPart.setBounds(240/15, 780/15, 64, 16);
		jlblPart.setFont(new Font("宋体", Font.PLAIN, (int)15));
		con.add(jlblPart);

		jlblTopic = new JLabel("上机时间规划");
		jlblTopic.setBounds(170, 0, 180, 29);
		jlblTopic.setFont(new Font("隶书", Font.PLAIN, (int)24));
		jlblTopic.setForeground(new Color(0x000000FF));
		con.add(jlblTopic);

		for(int i = 0;i < 7;i++)
		{
			
			jlblWeek[i]=new JLabel(weekArray[i]);//注意！！！！
			jlblWeek[i].setFont(normalFont);
			jlblWeek[i].setBounds(116+56*i, 120, 48, 16);
			jlblWeek[i].setName("week"+i);
			mainFrame.add(jlblWeek[i]);
		}
		for(int i = 0;i < 7;i++)
		{
			jlblTime[i]=new JLabel(timeArray[i]);//注意！！！！
			jlblTime[i].setFont(normalFont);
			jlblTime[i].setBounds(16, 152+32*i,88 ,16 );
			jlblTime[i].setName("time"+i);
			mainFrame.add(jlblTime[i]);
		}
		for(int i = 0;i<49;i++)
		{
			int x = i%7,y = i/7;
			
				jbtn[i] = new JButton();
				jbtn[i].setFont(new Font("宋体", Font.PLAIN, (int)14));
				jbtn[i].setBounds(112+56*x, 144+32*y, 57, 33);
				jbtn[i].setText("80");
				jbtn[i].setName("jbtn"+i);
				mainFrame.add(jbtn[i]);
		}
		for(int i = 0 ;i<49;i++)
		{
			jbtn[i].setToolTipText("点击修改某个时间段的可用计算机数量");
			jbtn[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		for(int i = 0;i<7;i++)
		{
			jlblWeek[i].setToolTipText("点击修改一天的可用计算机数量");
			jlblTime[i].setToolTipText("点击修改一周同一时间段的可用计算机数量");
		}
	}
	/**
	 * 
	  * 向数据库添加数据。
	  * @param       无。
	  * @return       无。
	  * @exception    无。
	 */
	public void add()
	{
//		String comId= ((String)jcmbComputer.getSelectedItem()).substring(0, 0 + 4);
		//String SQLString = "insert into SYS_TIME_SECTION_INFO (TIME_SECTION_ID，USABLE_COUNT,COMPUTER_ROOM_ID) ";
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
	  *	 界面居中。
	  * @param       无。
	  * @return       无。
	  * @exception    无。
	 */
	public Dimension getScreenSize()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	/**
	 * 
	  * 		主函数
	  * @param      字符串
	  * @return      无
	  * @exception   无
	 */
	public static void main(String[] args)
	{
		new  ComputerTimePlanning();
	}
	
}
