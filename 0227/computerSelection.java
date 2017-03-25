
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
	private JFrame jfrmMain;//��������
	private Container con;//��������
		
	private JLabel jlblTopic;//��������

	
	private JLabel jlblComputerTime;//��������ʱѡ�񡱱�ǩ
	//��������ʱѡ����Ϣ�б�
	private DefaultListModel dlmComputerTime;
	private JList jlstComputerTime;
	private JScrollPane jspComputerTime;
	
	private JComboBox jcmbClassSelection;//�������γ�ѡ�������б�
	private JLabel jlblClassSelection;	//�������γ�ѡ�񡱱�ǩ
	private JLabel jlblShowNum;//��������ʾ�ϻ���������ı�ǩ
	
	private JLabel jlblStudentNum;//������ѧԱ��š���ǩ
	private JTextField jtxtStudentNum;//������дѧ����ŵ��ı���
	
	private JComboBox jcmbShowAll;//��������ʾ�������б�
	//����ѧԱ��Ϣ�б�
	private JList jlstShowAll;
	private JScrollPane jspShowAll;
	private DefaultListModel dlmShowAll;
	private JLabel jlblNumber;//���������м��ˡ���ǩ
		
	private JLabel jlblComputerSelect;//��������ʱѡ���б���ǩ
	private JLabel jlblSelectComputer;//������ѡ��������ı�ǩ
	private JComboBox jcmbSelectComputer;//��������ѡ�������б�
	
	/** �������ڱ�ע������*/
	private JLabel jlblWeek[]=new JLabel[7];
	
	/** ���������ʼ��*/
	private String weekArray[]={"����һ","���ڶ�","������","������","������","������","������"};
	
	/**����ʱ��α�ע������ */
	private JLabel jlblTime[]=new JLabel[7];
	
	/** ʱ��������ʼ��*/
	private String timeArray[]={"08:00-10:00","10:00-12:00","12:00-14:00","14:00-16:00","16:00-18:00","18:00-20:00","20:00-22:00"};
	
	private JLabel jlblShowNotice;//��ʾ��ʾ�û������󰴡�ȷ������ť�ı�ǩ	

    //������ʱѡ�����49����ť
	private JButton jbtn[] = new JButton[49];
	private int allAvailable[]=new int[49];
	private int leftAvaliable[]=new int[49];
	
	/**�����������ݿ���*/
	private Connection conn;
	/**����һ������� */
	private ResultSet rs;
	/**����һ���ӿ� */
	private java.sql.Statement statement;
	
	private JLabel jlblClassName;//��������ѡ�γ̡���ǩ
	private JLabel jlblClass;//�������γ̡���ǩ
	private JLabel jlblStudentName;//������ѧ����������ǩ
	private JLabel jlblName;//��������������ǩ
	
	private JLabel jlblImage;//������ѧԱ��Ƭ����ǩ
	
	private JButton jbtnExit;//�������˳�����ť
	private JButton jbtnEnsure;//������ȷ������ť
	
	private int status=0;
	
	//ѧ����ʱѡ��״̬
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
	  * ����ϵͳ��ʾ�Ի���
	  * @param       �ַ������赯�������ݡ�
	  * @return      �ޡ�
	  * @exception   �ޡ�
	 */
	/*private void showMessage(String mes)
	{
		JOptionPane.showConfirmDialog(jfrmMain, mes, "ϵͳ��ܰ��ʾ", JOptionPane.OK_CANCEL_OPTION);
	}
	*/
	/**
	* @����  �����¼�
	*@param   �ޡ�
	*@return   �ޡ�
	*@exception  �ޡ�
	*/
	private void dealAction() 
	{
		
		/*�γ�ѡ�������б�_click����
		{
		 	�û�������γ�ѡ���б��С� ��ĳһ�
		  SELECT Subject_ Practic_Time
		  FROM  USER_SUBJECT_INFO
		  WHERE  ��Ŀ��� = ѡ����Ŀγ̱���еĿ�Ŀ���
		   ÿ���ϻ�����Num = getInt(Subject_ Practic_Time)
		   ����Ϊ�����γ�ÿ�ܶ����ΪNum��
		   Ĭ�ϡ���ʾ�������б��һ��Ϊѡ���
		   �õ�ѧԱ״̬status������ʾ�����б��һ�����
			ѧԱ�б�_��ʼ����status����
			����ѡ�������б�_��ʼ����ѡ����Ŀγ̱�ţ���
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
		
		

			//�˳�����¼�
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
	* @����   ��ʼ�����������б�
	*@param 	�ޡ�
	*@return	��
	*@exception   �ޡ�
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
		//��ʱѡ������ʼ�������������б��е�ѡ�����
		initJbtnMatrix((String)jcmbSelectComputer.getSelectedItem());
		initClassSelection() ;
	}
	/**
	* @����  ��ʼ�����γ�ѡ�������б�
	*@param  ��
	*@return  �ޡ�
	*@exception  �ޡ�
	*/
	private void initClassSelection() 
	{
		//��ѯ�γ̱��
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
		
		deal(str);//���γ̱�źͿγ�������ӵ����γ�ѡ�������б��в���ʾ��
		jcmbShowAll.setSelectedIndex(0);
		status=Get_Studentstaus((String) jcmbShowAll.getSelectedItem());
		//System.out.print(jcmbShowAll.getSelectedItem());
		initStudentList(status);//��ʼ��ѧԱ��Ϣ�б�
	}
	
	/**
	* @����   ��ʼ��ѧԱ��Ϣ�б�
	*@param   ����   ѧ����ʱѡ��״̬��
	*@return     �ޡ�
	*@exception    �ޡ�
	*/
	private void initStudentList(int status)
	{
		jlstShowAll.removeAll();//���ѧԱ��Ϣ�б�
		String SQLString="";
		if(status==NOT_SELECT_STATUS)//����ѧԱδѡ��ʱ��
		{
			SQLString+="SELECT  USER_STUDENT_INFO.Student_Id , Student_Name FROM  USER_STUDENT_INFO,SYS_PAYMENT_INFO ";
			SQLString+="WHERE  Student_Section_Status=��0�� AND Student_Satus IN('0','1') ";
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
					jlblNumber.setText("����"+Count+"��");
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
			disConnection();
		}
		else //����ѧԱ��ѡ��ʱ��
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
					jlblNumber.setText("����"+Count+"��");
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
	* @����   �õ�ѧԱ��ʱѡ���״̬��
	*@param    �ַ�����   ��ʾ�����б��еĵ�ǰѡ���
	*@return    ����   ѧ����ʱѡ��״̬��
	*@exception   �ޡ�
	*/
	private int Get_Studentstaus(String selectedItem) 
	{
		int  str;
		if(selectedItem.equals("��ʾδѡ��ʱѧԱ"))
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
			//�ɿγ̱�ŵõ��γ����ơ�
			String str1 = str.substring(i, i+9)+ " "+ Get_CourseName(str.substring(i, i+9));
			i = i + 9;
			jcmbClassSelection.addItem(str1);
		}	
		jcmbClassSelection.setSelectedIndex(0);//���Ĭ�����ڿγ�ѡ�������б��һ����
		//�趨����ʾ�ϻ���������ı�ǩ
		Set_ShowNum(((String)jcmbClassSelection.getSelectedItem()).substring(0, 0+9));
	}
	
	/**
	* @����   ���ݿγ������趨����ʾ�ϻ���������ı�ǩ
	*@param    �ַ�����    ��Ŀ���
	*@return    �ޡ� 
	*@exception   �ޡ�
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
				jlblShowNum.setText("�ÿγ�ÿ�ܶ�ϻ�"+i+"��");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	   disConnection();
	}
	
	/**
	* @����  �ɿγ̱�ŵÿγ�����  
	*@param   �ַ�����    �γ̱��
	*@return   �ַ�����    �γ�����
	*@exception  �ޡ�
	*/
	private String Get_CourseName(String courseId) //�õ��γ����ơ�
	{
		String str= courseId.substring(0, 0+2)+"��";
		if(Integer.parseInt(courseId.substring(2, 2+2)) >= 7)
			str+= "�＾";
		else
			str+= "����";
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
		str+=courseId.substring(8, 8+1)+"��";
		return str;
	}
	
	/**
	* @����   ��ʼ����ʱ����
	*@param   �ַ�����      
	*@return
	*@exception
	*/
	private void initJbtnMatrix(String practiceroomId)
	{
		//GET_��ǰʱ�ο��û����������������б��е�ѡ�����
		Get_valaible(practiceroomId);
		//GET_��ǰʱ��ʣ����û��������������б��е�ѡ�����
		Get_leftValiable(practiceroomId);
		//��ʼ�����󣨣���
		initJbtn();
	}

	/**
	* @����    �õ���ǰ������ʱ��εĿ��û�����
	*@param   �ַ�����    �������
	*@return   �� 
	*@exception   �ޡ�
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
	* @����    �õ���ǰ������ʱ��ε�ʣ����û�����
	*@param   �ַ�����    �������
	*@return   �ޡ�
	*@exception  �ޡ�
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
	* @����   ��ʼ����ʱѡ�����ť�ϵ�����
	*@param    �ޡ�
	*@return   �ޡ�
	*@exception   �ޡ�
	*/
	private void initJbtn()
	{
		// TODO Auto-generated method stub
		for(int i = 0; i < 49; i ++)
	      jbtn[i].setText("" + leftAvaliable[i] + "/" + allAvailable[i]);
	}
	
	/**
	* @����  �������ݿ⡣
	*@param   �ޡ�
	*@return   �ޡ�
	*@exception  �ޡ�
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
			System.out.println("JdbcOdbcDriver������");
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	* @����   �Ͽ������ݿ�����ӡ�
	*@param   �ޡ�
	*@return   �ޡ�
	*@exception   �ޡ�
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
	

	//���ý�����С�
	public Dimension getScreenSize()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public void intiFrame()
	{
		Font normalFont = new Font("����", Font.PLAIN, (int) 15);//����Ĭ������

		//��������
		jfrmMain = new JFrame("����΢�����������Ϣϵͳ--�ϻ�ѡ��");
		jfrmMain.setResizable(false);
		con = jfrmMain.getContentPane();
		con.setLayout(null);

		jfrmMain.setSize(760, 565);
		jfrmMain.setResizable(false);
		jfrmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrmMain.setLocation((getScreenSize().width - jfrmMain.getWidth())/2, (getScreenSize().height - jfrmMain.getHeight())/2);
		jfrmMain.setVisible(true);

		//��������ʱѡ���б��ı�ǩ
		jlblComputerSelect = new JLabel("��ʱѡ���б�");
		jlblComputerSelect.setBounds(448, 56, 105, 25);
		jlblComputerSelect.setFont(normalFont);
		jlblComputerSelect.setToolTipText("��ʱѡ�����");
		con.add(jlblComputerSelect);
		
		//������ѡ���������ǩ
		jlblSelectComputer = new JLabel("ѡ�����");
		jlblSelectComputer.setBounds(592, 56, 73, 25);
		jlblSelectComputer.setFont(normalFont);
		jlblSelectComputer.setToolTipText("ѡ�������ǩ");
		con.add(jlblSelectComputer);
		
		//������ѡ��������������б�
		jcmbSelectComputer = new JComboBox();
		jcmbSelectComputer.setBounds(672, 56, 65, 24);
		jcmbSelectComputer.setFont(normalFont);
		jcmbSelectComputer.setToolTipText("���ѡ�����");
		con.add(jcmbSelectComputer);

		//��������һ��������ı�ǩ
		for(int i = 0;i < 7;i++)
		{
			jlblWeek[i]=new JLabel(weekArray[i]);//ע�⣡������
			jlblWeek[i].setFont(normalFont);
			jlblWeek[i].setBounds(292+64*i, 88, 57, 25);
			jlblWeek[i].setName("week"+i);
			con.add(jlblWeek[i]);
		}
		

		//����7��ʱ���
		for(int i = 0;i < 7;i++)
		{
			jlblTime[i]=new JLabel(timeArray[i]);//ע�⣡������
			jlblTime[i].setFont(normalFont);
			jlblTime[i].setBounds(192, 116+32*i,89 ,25 );
			jlblTime[i].setName("time"+i);
			con.add(jlblTime[i]);
		}

		//��������ʱѡ�����49����ť
		
		for(int i = 0;i<49;i++)
		{
			int x = i%7,y = i/7;
			
				jbtn[i] = new JButton();
				jbtn[i].setFont(new Font("����", Font.PLAIN, (int)12));
				jbtn[i].setBounds(288+64*x, 112+32*y, 65, 33);
				jbtn[i].setText("50/50");
				jbtn[i].setName("jbtn"+i);
				con.add(jbtn[i]);
		}

		for(int i = 0 ;i<49;i++)
		{
			jbtn[i].setToolTipText("�����ʱ��ο��޸�ѧԱ��ʱѡ����Ϣ");
			jbtn[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		//�������˳�����ť
		jbtnExit = new JButton("�˳�");
		jbtnExit.setBounds(664, 472, 65, 33);
		jbtnExit.setFont(normalFont);
		jbtnExit.setToolTipText("����رս���");		
		con.add(jbtnExit);

		//������ȷ������ť
		jbtnEnsure = new JButton("ȷ��");
		jbtnEnsure.setBounds(664, 440, 65, 33);
		jbtnEnsure.setFont(normalFont);
		jbtnEnsure.setToolTipText("��������ʱѡ����Ϣ");
		con.add(jbtnEnsure);

		//��������ʱѡ�񡱱�ǩ
		jlblComputerTime = new JLabel("��ʱѡ��");
		jlblComputerTime.setBounds(224, 472, 65, 25);
		jlblComputerTime.setFont(normalFont);
		jlblComputerTime.setToolTipText("��ʱѡ����Ϣ");
		con.add(jlblComputerTime);
		
		//��������ʱѡ����Ϣ�б�
		dlmComputerTime = new DefaultListModel();
		jlstComputerTime = new JList(dlmComputerTime);
		jspComputerTime = new JScrollPane(jlstComputerTime);
		jspComputerTime.setBounds(296, 448, 217, 68);
		jspComputerTime.setFont(normalFont);
		jlstComputerTime.setToolTipText("��ѧԱ��ʱѡ����Ϣ");
		con.add(jspComputerTime);
		
		//�������γ�ѡ�񡱱�ǩ
		jlblClassSelection = new JLabel("�γ�ѡ��");
		jlblClassSelection.setBounds(112, 56, 65, 25);
		jlblClassSelection.setFont(normalFont);
		jlblClassSelection.setToolTipText("�γ�ѡ��");
		con.add(jlblClassSelection);
		
		//�������γ�ѡ�������б�
		jcmbClassSelection = new JComboBox();
		jcmbClassSelection.setBounds(8, 80, 273, 24);
		jcmbClassSelection.setFont(normalFont);
		jcmbClassSelection.setToolTipText("���ѡ��γ�");
		con.add(jcmbClassSelection);
		
		//�������γ̶�ϻ��������ı�ǩ
		jlblShowNum = new JLabel("�˿γ�ÿ�ܶ�ϻ�3��");
		jlblShowNum.setBounds(8, 104, 153, 17);
		jlblShowNum.setFont(new Font("����", Font.PLAIN, (int)13));
		jlblShowNum.setForeground(new Color(0x000000FF));
		jlblShowNum.setToolTipText("�ÿγ̶�ϻ�����");
		con.add(jlblShowNum);
		
		//������ѧԱ��š���ǩ
		jlblStudentNum = new JLabel("ѧԱ���");
		jlblStudentNum.setBounds(60, 128, 65, 25);
		jlblStudentNum.setFont(normalFont);
		jlblStudentNum.setToolTipText("ѧԱ���");
		con.add(jlblStudentNum);
		
		//����������ѧԱ��š��ı���
		jtxtStudentNum = new JTextField();
		jtxtStudentNum.setBounds(8, 152, 169, 24);
		jtxtStudentNum.setFont(normalFont);
		jtxtStudentNum.setToolTipText("����ѧԱ���");
		con.add(jtxtStudentNum);
		
		//��������ʾ����Ϣ�����б�
		jcmbShowAll = new JComboBox();
		jcmbShowAll.addItem("��ʾ��ѡ��ʱѧԱ");
		jcmbShowAll.addItem("��ʾδѡ��ʱѧԱ");
		jcmbShowAll.setBounds(8, 192, 169, 24);
		jcmbShowAll.setFont(normalFont);
		jcmbShowAll.setToolTipText("���ѡ����ʾ��ѧ����Ϣ");
		con.add(jcmbShowAll);
		
		//������ѧԱ��Ϣ�б�
		dlmShowAll = new DefaultListModel();
		jlstShowAll = new JList(dlmShowAll);
		jspShowAll = new JScrollPane(jlstShowAll);
		jspShowAll.setBounds(8, 216, 169, 276);
		jspShowAll.setFont(normalFont);
		jlstShowAll.setToolTipText("ѧ����Ϣ�б�");
		con.add(jspShowAll);

		//���������м��ˡ��ı�ǩ
		jlblNumber = new JLabel("����0��");
		jlblNumber.setBounds(8, 496, 73, 25);
		jlblNumber.setFont(normalFont);
		jlblNumber.setToolTipText("��ǰ״̬�µ�ѧԱ����");
		con.add(jlblNumber);

		//������ʾ�û������ȷ������ť�ı�ǩ
		jlblShowNotice = new JLabel("����ѧԱ��ʱ���谴ȷ����ť�����������Ч");
		jlblShowNotice.setBounds(6840/15, 5160/15, 4455/15, 255/15);
		jlblShowNotice.setFont(new Font("����", Font.PLAIN, (int)13));
		jlblShowNotice.setForeground(new Color(0x00FF0000));
		jlblShowNotice.setToolTipText("��ʾ�û�����");
		con.add(jlblShowNotice);

		//������ѧԱ��Ƭ���ı�ǩ
		jlblImage = new JLabel("ѧԱ��Ƭ");
		jlblImage.setBounds(544, 376, 81, 17);
		jlblImage.setFont(normalFont);
		jlblImage.setToolTipText("ѧԱ��Ƭ");
		con.add(jlblImage);

		//������ѧ���������ı�ǩ
		jlblStudentName = new JLabel("ѧԱ����");
		jlblStudentName.setBounds(224, 376, 65, 25);
		jlblStudentName.setFont(normalFont);
		jlblStudentName.setToolTipText("ѧ������");
		con.add(jlblStudentName);

		//������ʾѧ�������ı�ǩ
		jlblName = new JLabel("");
		jlblName.setBounds(296, 376, 217, 25);
		jlblName.setFont(normalFont);
		jlblName.setToolTipText("��ʾѧ������");
		con.add(jlblName);

		//��������ѡ�γ̡��ı�ǩ
		jlblClassName = new JLabel("��ѡ�γ�");
		jlblClassName.setBounds(224, 416, 65, 25);
		jlblClassName.setFont(normalFont);
		jlblClassName.setToolTipText("ѧ����ѡ�γ�");
		con.add(jlblClassName);

		//������ʾ��ѡ�γ̵ı�ǩ
		jlblClass = new JLabel("");
		jlblClass.setBounds(296, 416, 217, 25);
		jlblClass.setFont(normalFont);
		jlblClass.setToolTipText("��ʾ��ѡ�γ�����");
		con.add(jlblClass);
		
		//�������ϻ�ѡ�񡱱���
		jlblTopic = new JLabel("�ϻ�ѡ��");
		jlblTopic.setBounds(315, 0, 121, 33);
		jlblTopic.setFont(new Font("����", Font.PLAIN, (int)24));
		jlblTopic.setForeground(new Color(0x000000FF));
		jlblTopic.setToolTipText("�������");
		con.add(jlblTopic);
	}
	
	public static void main(String[] args)
	{
		new computerSelection();
	}
}