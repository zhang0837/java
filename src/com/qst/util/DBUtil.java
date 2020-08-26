package com.qst.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class DBUtil {
	// DBUtil����޲ι��췽��
	private DBUtil() {
	}

	// 1.��̬����:��Ҫ����Ŀ������ʱ��ͳ�ʼ��,�ڲ���������������,���ִ����Ǳ���ִ�е�(��̬����������ص�ʱ����Ѿ�����,����������ֱ�ӵ���)
	// ʹ����ľ�̬����ʱ,ע��:
	// a.�ھ�̬������ֻ��ֱ�ӵ���ͬ���е�������̬��Ա(���������ͷ���),������ֱ�ӷ������еķǾ�̬��Ա(������Ϊ,���ڷǾ�̬�ķ����ͱ���,��Ҫ�ȴ������ʵ����������ʹ��,����̬������ʹ��ǰ���ô����κζ���)
	// b.��̬�����������κη�ʽ����this��super�ؼ���,ӦΪ��̬������ʹ��ǰ���ô����κ�ʵ������,����̬��������ʱ��,this�����õĶ������û�в���
	// c.��̬����������������ı���,����������ĳ�������,ע�ⲻ�ܰ��κη����ڵı��������ɾ�̬
	// 2.��̬�����;������ʱ���ȼ���,��ֻ���״ε���ʱ�Ż����
	// ��Щ�����������Ŀ����ʱ��ִ��,���ִ���������ִ�е�(���౻����ʱ,��̬����鱻ִ��,��ֻ��ִ��һ��,��̬����鳣�ù���ִ�������Եĳ�ʼ��)
	// 3.����:���ߵ��������:��̬��������Զ�ִ�е�,��̬�����Ǳ�����ʱ��ű�ִ��
	// URLָ��Ҫ���ʵ����ݿ���test
	static String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	// MySQL����ʱ���û���
	static String username = "root";
	// MySQL����ʱ������
	static String password = "1234";
	static {
		try {
			// ������������
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeErrorException(null, e + "���ݿ�����ʧ��");
		}
	}
	// ����˽�о�̬Connection����con;
	private static Connection con;

	// ����getConnect()����;����Connection���͵Ĳ���
	public static Connection getConnection() throws SQLException {
		// ����getConnection()��������MYDQL���ݿ�
		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	// �ر����ݿ�����
	public static void colse(ResultSet rs, PreparedStatement pstmt, Connection conn) throws SQLException {
		if (con != null && !con.isClosed()) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null && !pstmt.isClosed()) {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null && !conn.isClosed()) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
