
/**
 * @author 123
 * @version{2013/07/25}
 * @see
 * @since JDK
 */
import java.awt.Container;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class computerSelection
{
	private JFrame jfrmMain;//创建窗体
	private Container con;//创建容器
		
	private JLabel jlblTopic;//创建标题

	
	private JLabel jlblComputerTime;//创建“机时选择”标签
	//创建“机时选择”信息列表
	private DefaultListModel dlmComputerTime;
	private JList jlstComputerTime;
	private JScrollPane jspComputerTime;
	
	private JComboBox jcmbClassSelection;//创建“课程选择”下拉列表
	private JLabel jlblClassSelection;	//创建“课程选择”标签
	private JLabel jlblShowNum;//创建“显示上机额定次数”的标签
	
	private JLabel jlblStudentNum;//创建“学员编号”标签
	private JTextField jtxtStudentNum;//创建填写学生编号的文本框
	
	private JComboBox jcmbShowAll;//创建“显示”下拉列表
	//创建学员信息列表
	private JList jlstShowAll;
	private JScrollPane jspShowAll;
	private DefaultListModel dlmShowAll;
	private JLabel jlblNumber;//创建“共有几人”标签
		
	private JLabel jlblComputerSelect;//创建“机时选择列表”标签
	private JLabel jlblSelectComputer;//创建“选择机房”的标签
	private JComboBox jcmbSelectComputer;//创建机房选择下拉列表
	
	/** 定义星期标注的数组*/
	private JLabel jlblWeek[]=new JLabel[7];
	
	/** 星期数组初始化*/
	private String weekArray[]={"星期一","星期二","星期三","星期四","星期五","星期六","星期天"};
	
	/**定义时间段标注的数组 */
	private JLabel jlblTime[]=new JLabel[7];
	
	/** 时间段数组初始化*/
	private String timeArray[]={"08:00-10:00","10:00-12:00","12:00-14:00","14:00-16:00","16:00-18:00","18:00-20:00","20:00-22:00"};
	
	private JLabel jlblShowNotice;//显示提示用户操作后按“确定”按钮的标签	

    //创建机时选择矩阵49个按钮
	private JButton jbtn[] = new JButton[49];
	private int allAvailable[]=new int[49];
	private int leftAvaliable[]=new int[49];
	
	/**定义链接数据库类*/
	private Connection conn;
	/**定义一个结果集 */
	private ResultSet rs;
	/**定义一个接口 */
	private java.sql.Statement statement;
	
	private JLabel jlblClassName;//创建“所选课程”标签
	private JLabel jlblClass;//创建“课程”标签
	private JLabel jlblStudentName;//创建“学生姓名”标签
	private JLabel jlblName;//创建“姓名”标签
	
	private JLabel jlblImage;//创建“学员照片”标签
	
	private JButton jbtnExit;//创建“退出”按钮
	private JButton jbtnEnsure;//创建“确定”按钮
	
	private int status=0;
	
	//学生机时选择状态
	final static  int NOT_SELECT_STATUS=1;
	final static  int SELECT_STATUS=0;
	
	private int Count;
	
	public computerSelection()
	{
		intiFrame();
		dealAction();	
		intiPracticRoom();

	}
	/**
	 * 
	  * 弹出系统提示对话框
	  * @param       字符串，需弹出的内容。
	  * @return      无。
	  * @exception   无。
	 */
	/*private void showMessage(String mes)
	{
		JOptionPane.showConfirmDialog(jfrmMain, mes, "系统温馨提示", JOptionPane.OK_CANCEL_OPTION);
	}
	*/
	/**
	* @功能  处理事件
	*@param   无。
	*@return   无。
	*@exception  无。
	*/
	private void dealAction() 
	{
		
		/*课程选择下拉列表_click（）
		{
		 	用户点击“课程选择列表中” 的某一项。
		  SELECT Subject_ Practic_Time
		  FROM  USER_SUBJECT_INFO
		  WHERE  科目编号 = 选中项的课程编号中的科目编号
		   每周上机次数Num = getInt(Subject_ Practic_Time)
		   设置为：本课程每周额定次数为Num。
		   默认“显示”下拉列表第一项为选中项。
		   得到学员状态status（“显示下拉列表第一项”）。
			学员列表_初始化（status）。
			机房选择下拉列表_初始化（选中项的课程编号）。
		}
		*/
		jcmbClassSelection.addMouseListener
		(
				new MouseListener()
				{
					public void mouseClicked(MouseEvent arg0) 
					{
						Set_ShowNum(((String)jcmbClassSelection.getSelectedItem()).substring(0, 0+9));
						jcmbShowAll.setSelectedIndex(0);
						status=Get_Studentstaus((String) jcmbShowAll.getSelectedItem());
						initStudentList(status);
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
		
		

			//退出点击事件
			jbtnExit.addActionListener
			(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						jfrmMain.dispose();
					}
					
				}
			);
		
	}
	
	/**
	* @功能   初始化机房下拉列表
	*@param 	无。
	*@return	无
	*@exception   无。
	*/
	private void intiPracticRoom()
	{
		// TODO Auto-generated method stub
		jcmbSelectComputer.removeAllItems();
		
		String SQLString=" SELECT  Practise_Room_ID   FROM SYS_PRACTISEROOM_INFO ";
		//System.out.print(SQLString);
		connection();
		try 
		{
			rs=statement.executeQuery(SQLString);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			while (rs.next())
			{
				String str=rs.getString("Practise_Room_ID");
				jcmbSelectComputer.addItem(str);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		jcmbSelectComputer.setSelectedIndex(0);
		disConnection();
		//机时选择矩阵初始化（机房下拉列表中的选中项）。
		initJbtnMatrix((String)jcmbSelectComputer.getSelectedItem());
		initClassSelection() ;
	}
	/**
	* @功能  初始化“课程选择下拉列表”
	*@param  无
	*@return  无。
	*@exception  无。
	*/
	private void initClassSelection() 
	{
		//查询课程编号
		String SQLString="SELECT"+" "+"Course_Id"+" "+"FROM"+" "+ "COURSE_MANAGE_TWO_INFO";
		StringBuffer str = new StringBuffer();
		connection();
		try
		{
			rs=statement.executeQuery(SQLString);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			while(rs.next())
			{
				 str.append(rs.getString("Course_Id").trim()); 
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		disConnection();
		
		deal(str);//将课程编号和课程名称添加到“课程选择下拉列表”中并显示。
		jcmbShowAll.setSelectedIndex(0);
		status=Get_Studentstaus((String) jcmbShowAll.getSelectedItem());
		//System.out.print(jcmbShowAll.getSelectedItem());
		initStudentList(status);//初始化学员信息列表。
	}
	
	/**
	* @功能   初始化学员信息列表
	*@param   整型   学生机时选择状态。
	*@return     无。
	*@exception    无。
	*/
	private void initStudentList(int status)
	{
		jlstShowAll.removeAll();//清空学员信息列表。
		String SQLString="";
		if(status==NOT_SELECT_STATUS)//若该学员未选机时；
		{
			SQLString+="SELECT  USER_STUDENT_INFO.Student_Id , Student_Name FROM  USER_STUDENT_INFO,SYS_PAYMENT_INFO ";
			SQLString+="WHERE  Student_Section_Status=’0’ AND Student_Satus IN('0','1') ";
			SQLString+="AND USER_STUDENT_INFO.Student_Id=SYS_PAYMENT_INFO.Student_Id";
			Count=0;
			connection();
			try 
			{
				rs=statement.executeQuery(SQLString);
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				while(rs.next())
				{
					String str=rs.getString("Student_Id")+"  "+rs.getString("Student_Name");
					dlmShowAll.addElement(str);
					Count++;
					jlblNumber.setText("共有"+Count+"人");
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
			disConnection();
		}
		else //若该学员已选机时；
		{
			SQLString="SELECT distinct USER_STUDENT_INFO.Student_Id,Student_Name ";
			SQLString+=" FROM  USER_STUDENT_INFO,SYS_PAYMENT_INFO ";
			SQLString+=" WHERE Student_Section_Status='1' ";
			SQLString+=" AND USER_STUDENT_INFO.Student_Id=SYS_PAYMENT_INFO.Student_Id";
			Count=0;
			connection();
			try 
			{
				rs=statement.executeQuery(SQLString);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				while(rs.next())
				{
					String str = rs.getString ("Student_Id") + " " +rs.getString("Student_Name");
					dlmShowAll.addElement(str);
					Count++;
					jlblNumber.setText("共有"+Count+"人");
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			disConnection();
		}
		
	}

	/**
	* @功能   得到学员机时选择的状态。
	*@param    字符类型   显示下拉列表中的当前选中项。
	*@return    整型   学生机时选择状态。
	*@exception   无。
	*/
	private int Get_Studentstaus(String selectedItem) 
	{
		int  str;
		if(selectedItem.equals("显示未选机时学员"))
			str=NOT_SELECT_STATUS;
		else 
			str=SELECT_STATUS;		
		return str;
	}
	private void deal(StringBuffer str)
	{
		int i = 0;
		while(i <= str.length()-9)
		{
			//由课程编号得到课程名称。
			String str1 = str.substring(i, i+9)+ " "+ Get_CourseName(str.substring(i, i+9));
			i = i + 9;
			jcmbClassSelection.addItem(str1);
		}	
		jcmbClassSelection.setSelectedIndex(0);//光标默认置于课程选择下拉列表第一个。
		//设定“显示上机额定次数”的标签
		Set_ShowNum(((String)jcmbClassSelection.getSelectedItem()).substring(0, 0+9));
	}
	
	/**
	* @功能   根据课程名称设定“显示上机额定次数”的标签
	*@param    字符类型    科目编号
	*@return    无。 
	*@exception   无。
	*/
	private void Set_ShowNum( String  courseId)
	{
		// TODO Auto-generated method stub
		String SQLString="SELECT"+" Subject_Practic_Time " +" FROM "+" USER_SUBJECT_INFO";
		SQLString+=" WHERE "+" Subject_Id = '" + courseId.substring(6,6+2) + "';"; 	
		connection();
		try 
		{
			rs=statement.executeQuery(SQLString);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			while(rs.next())
			{
				int i=rs.getInt("Subject_Practic_Time"); 
				jlblShowNum.setText("该课程每周额定上机"+i+"次");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	   disConnection();
	}
	
	/**
	* @功能  由课程编号得课程名称  
	*@param   字符类型    课程编号
	*@return   字符类型    课程名称
	*@exception  无。
	*/
	private String Get_CourseName(String courseId) //得到课程名称。
	{
		String str= courseId.substring(0, 0+2)+"年";
		if(Integer.parseInt(courseId.substring(2, 2+2)) >= 7)
			str+= "秋季";
		else
			str+= "春季";
		String SQLString = "SELECT "+" "+"Subject_Name"+" "+ "FROM"+" "+" USER_SUBJECT_INFO";
		SQLString+=" WHERE"+" "+ "Subject_ID = '" + courseId.substring(6, 6+2) + "';"; 	
		//System.out.println(SQLString);
		connection();
		try
		{
			rs=statement.executeQuery(SQLString);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			while(rs.next())
			{
				 str+=rs.getString("Subject_Name");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		disConnection();
		str+=courseId.substring(8, 8+1)+"班";
		return str;
	}
	
	/**
	* @功能   初始化机时矩阵
	*@param   字符类型      
	*@return
	*@exception
	*/
	private void initJbtnMatrix(String practiceroomId)
	{
		//GET_当前时段可用机总数（机房下拉列表中的选中项）。
		Get_valaible(practiceroomId);
		//GET_当前时段剩余可用机数（机房下拉列表中的选中项）。
		Get_leftValiable(practiceroomId);
		//初始化矩阵（）；
		initJbtn();
	}

	/**
	* @功能    得到当前机房该时间段的可用机总数
	*@param   字符类型    机房编号
	*@return   无 
	*@exception   无。
	*/
	private void Get_valaible(String practiceroomId)
	{
		// TODO Auto-generated method stub
		String SQLString = "SELECT Time_Section_Id,Usable_Count FROM SYS_TIME_SECTION_INFO WHERE ";
		SQLString += "COMPUTER_ROOM_ID = " + "'" + practiceroomId + "'";
		connection();
		try 
		{
			rs=statement.executeQuery(SQLString);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try 
		{
			while(rs.next())
			{
					String str = rs.getString("TIME_SECTION_ID");
					int j = Integer.valueOf(str.substring(0,1));
					int i = Integer.valueOf(str.substring(1, 2));
					int index = (j-1)*7+(i-1);
					int str1 = rs.getInt("USABLE_COUNT");	
					allAvailable[index]=str1;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		disConnection();
	}
	
	/**
	* @功能    得到当前机房该时间段的剩余可用机总数
	*@param   字符类型    机房编号
	*@return   无。
	*@exception  无。
	*/
	private void Get_leftValiable(String practiceroomId) 
	{
		// TODO Auto-generated method stub
		leftAvaliable=allAvailable;
		String  SQLString  = "SELECT  Time_section_Id    FROM SYS_COMPUTER_SELECTION_INFO   ";
				SQLString+="WHERE  Prctise_Room_Id = '"+practiceroomId+"'";
		//System.out.print(SQLString);
		connection();
	  
		try 
		{
			rs=statement.executeQuery(SQLString);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try 
		{
			while(rs.next())
			{
					String str = rs.getString("TIME_SECTION_ID");
					int j = Integer.valueOf(str.substring(0,1));
					int i = Integer.valueOf(str.substring(1, 2));
					int index = (j-1)*7+(i-1);
					int str1 = rs.getInt("USABLE_COUNT");	
					leftAvaliable[index]=str1-1;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnection();
	}	
	
	/**
	* @功能   初始化机时选择矩阵按钮上的数字
	*@param    无。
	*@return   无。
	*@exception   无。
	*/
	private void initJbtn()
	{
		// TODO Auto-generated method stub
		for(int i = 0; i < 49; i ++)
	      jbtn[i].setText("" + leftAvaliable[i] + "/" + allAvailable[i]);
	}
	
	/**
	* @功能  连接数据库。
	*@param   无。
	*@return   无。
	*@exception  无。
	*/
	private void connection()
	{
		// TODO Auto-generated method stub

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
	* @功能   断开与数据库的连接。
	*@param   无。
	*@return   无。
	*@exception   无。
	*/
	private void disConnection()
	{
		try 
		{
			statement.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	

	//设置界面居中。
	public Dimension getScreenSize()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public void intiFrame()
	{
		Font normalFont = new Font("宋体", Font.PLAIN, (int) 15);//设置默认字体

		//创建窗体
		jfrmMain = new JFrame("西安微码教育管理信息系统--上机选择");
		jfrmMain.setResizable(false);
		con = jfrmMain.getContentPane();
		con.setLayout(null);

		jfrmMain.setSize(760, 565);
		jfrmMain.setResizable(false);
		jfrmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrmMain.setLocation((getScreenSize().width - jfrmMain.getWidth())/2, (getScreenSize().height - jfrmMain.getHeight())/2);
		jfrmMain.setVisible(true);

		//创建“机时选择列表”的标签
		jlblComputerSelect = new JLabel("机时选择列表");
		jlblComputerSelect.setBounds(448, 56, 105, 25);
		jlblComputerSelect.setFont(normalFont);
		jlblComputerSelect.setToolTipText("机时选择矩阵");
		con.add(jlblComputerSelect);
		
		//创建“选择机房”标签
		jlblSelectComputer = new JLabel("选择机房");
		jlblSelectComputer.setBounds(592, 56, 73, 25);
		jlblSelectComputer.setFont(normalFont);
		jlblSelectComputer.setToolTipText("选择机房标签");
		con.add(jlblSelectComputer);
		
		//创建“选择机房”的下拉列表
		jcmbSelectComputer = new JComboBox();
		jcmbSelectComputer.setBounds(672, 56, 65, 24);
		jcmbSelectComputer.setFont(normalFont);
		jcmbSelectComputer.setToolTipText("点击选择机房");
		con.add(jcmbSelectComputer);

		//创建星期一到星期天的标签
		for(int i = 0;i < 7;i++)
		{
			jlblWeek[i]=new JLabel(weekArray[i]);//注意！！！！
			jlblWeek[i].setFont(normalFont);
			jlblWeek[i].setBounds(292+64*i, 88, 57, 25);
			jlblWeek[i].setName("week"+i);
			con.add(jlblWeek[i]);
		}
		

		//创建7个时间段
		for(int i = 0;i < 7;i++)
		{
			jlblTime[i]=new JLabel(timeArray[i]);//注意！！！！
			jlblTime[i].setFont(normalFont);
			jlblTime[i].setBounds(192, 116+32*i,89 ,25 );
			jlblTime[i].setName("time"+i);
			con.add(jlblTime[i]);
		}

		//创建“机时选择矩阵”49个按钮
		
		for(int i = 0;i<49;i++)
		{
			int x = i%7,y = i/7;
			
				jbtn[i] = new JButton();
				jbtn[i].setFont(new Font("宋体", Font.PLAIN, (int)12));
				jbtn[i].setBounds(288+64*x, 112+32*y, 65, 33);
				jbtn[i].setText("50/50");
				jbtn[i].setName("jbtn"+i);
				con.add(jbtn[i]);
		}

		for(int i = 0 ;i<49;i++)
		{
			jbtn[i].setToolTipText("点击该时间段可修改学员机时选择信息");
			jbtn[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		//创建“退出”按钮
		jbtnExit = new JButton("退出");
		jbtnExit.setBounds(664, 472, 65, 33);
		jbtnExit.setFont(normalFont);
		jbtnExit.setToolTipText("点击关闭界面");		
		con.add(jbtnExit);

		//创建“确定”按钮
		jbtnEnsure = new JButton("确定");
		jbtnEnsure.setBounds(664, 440, 65, 33);
		jbtnEnsure.setFont(normalFont);
		jbtnEnsure.setToolTipText("点击保存机时选择信息");
		con.add(jbtnEnsure);

		//创建“机时选择”标签
		jlblComputerTime = new JLabel("机时选择");
		jlblComputerTime.setBounds(224, 472, 65, 25);
		jlblComputerTime.setFont(normalFont);
		jlblComputerTime.setToolTipText("机时选择信息");
		con.add(jlblComputerTime);
		
		//创建“机时选择”信息列表
		dlmComputerTime = new DefaultListModel();
		jlstComputerTime = new JList(dlmComputerTime);
		jspComputerTime = new JScrollPane(jlstComputerTime);
		jspComputerTime.setBounds(296, 448, 217, 68);
		jspComputerTime.setFont(normalFont);
		jlstComputerTime.setToolTipText("该学员机时选择信息");
		con.add(jspComputerTime);
		
		//创建“课程选择”标签
		jlblClassSelection = new JLabel("课程选择");
		jlblClassSelection.setBounds(112, 56, 65, 25);
		jlblClassSelection.setFont(normalFont);
		jlblClassSelection.setToolTipText("课程选择");
		con.add(jlblClassSelection);
		
		//创建“课程选择下拉列表”
		jcmbClassSelection = new JComboBox();
		jcmbClassSelection.setBounds(8, 80, 273, 24);
		jcmbClassSelection.setFont(normalFont);
		jcmbClassSelection.setToolTipText("点击选择课程");
		con.add(jcmbClassSelection);
		
		//创建“课程额定上机次数”的标签
		jlblShowNum = new JLabel("此课程每周额定上机3次");
		jlblShowNum.setBounds(8, 104, 153, 17);
		jlblShowNum.setFont(new Font("宋体", Font.PLAIN, (int)13));
		jlblShowNum.setForeground(new Color(0x000000FF));
		jlblShowNum.setToolTipText("该课程额定上机次数");
		con.add(jlblShowNum);
		
		//创建“学员编号”标签
		jlblStudentNum = new JLabel("学员编号");
		jlblStudentNum.setBounds(60, 128, 65, 25);
		jlblStudentNum.setFont(normalFont);
		jlblStudentNum.setToolTipText("学员编号");
		con.add(jlblStudentNum);
		
		//创建“输入学员编号”文本框
		jtxtStudentNum = new JTextField();
		jtxtStudentNum.setBounds(8, 152, 169, 24);
		jtxtStudentNum.setFont(normalFont);
		jtxtStudentNum.setToolTipText("输入学员编号");
		con.add(jtxtStudentNum);
		
		//创建“显示”信息下拉列表
		jcmbShowAll = new JComboBox();
		jcmbShowAll.addItem("显示已选机时学员");
		jcmbShowAll.addItem("显示未选机时学员");
		jcmbShowAll.setBounds(8, 192, 169, 24);
		jcmbShowAll.setFont(normalFont);
		jcmbShowAll.setToolTipText("点击选择显示的学生信息");
		con.add(jcmbShowAll);
		
		//创建“学员信息列表”
		dlmShowAll = new DefaultListModel();
		jlstShowAll = new JList(dlmShowAll);
		jspShowAll = new JScrollPane(jlstShowAll);
		jspShowAll.setBounds(8, 216, 169, 276);
		jspShowAll.setFont(normalFont);
		jlstShowAll.setToolTipText("学生信息列表");
		con.add(jspShowAll);

		//创建“共有几人”的标签
		jlblNumber = new JLabel("共有0人");
		jlblNumber.setBounds(8, 496, 73, 25);
		jlblNumber.setFont(normalFont);
		jlblNumber.setToolTipText("当前状态下的学员总数");
		con.add(jlblNumber);

		//创建提示用户点击“确定”按钮的标签
		jlblShowNotice = new JLabel("更改学员机时后，需按确定按钮，否则操作无效");
		jlblShowNotice.setBounds(6840/15, 5160/15, 4455/15, 255/15);
		jlblShowNotice.setFont(new Font("宋体", Font.PLAIN, (int)13));
		jlblShowNotice.setForeground(new Color(0x00FF0000));
		jlblShowNotice.setToolTipText("提示用户操作");
		con.add(jlblShowNotice);

		//创建“学员照片”的标签
		jlblImage = new JLabel("学员照片");
		jlblImage.setBounds(544, 376, 81, 17);
		jlblImage.setFont(normalFont);
		jlblImage.setToolTipText("学员照片");
		con.add(jlblImage);

		//创建“学生姓名”的标签
		jlblStudentName = new JLabel("学员姓名");
		jlblStudentName.setBounds(224, 376, 65, 25);
		jlblStudentName.setFont(normalFont);
		jlblStudentName.setToolTipText("学生姓名");
		con.add(jlblStudentName);

		//创建显示学生姓名的标签
		jlblName = new JLabel("");
		jlblName.setBounds(296, 376, 217, 25);
		jlblName.setFont(normalFont);
		jlblName.setToolTipText("显示学生姓名");
		con.add(jlblName);

		//创建“所选课程”的标签
		jlblClassName = new JLabel("所选课程");
		jlblClassName.setBounds(224, 416, 65, 25);
		jlblClassName.setFont(normalFont);
		jlblClassName.setToolTipText("学生所选课程");
		con.add(jlblClassName);

		//创建显示所选课程的标签
		jlblClass = new JLabel("");
		jlblClass.setBounds(296, 416, 217, 25);
		jlblClass.setFont(normalFont);
		jlblClass.setToolTipText("显示所选课程名称");
		con.add(jlblClass);
		
		//创建“上机选择”标题
		jlblTopic = new JLabel("上机选择");
		jlblTopic.setBounds(315, 0, 121, 33);
		jlblTopic.setFont(new Font("隶书", Font.PLAIN, (int)24));
		jlblTopic.setForeground(new Color(0x000000FF));
		jlblTopic.setToolTipText("窗体标题");
		con.add(jlblTopic);
	}
	
	public static void main(String[] args)
	{
		new computerSelection();
	}
}