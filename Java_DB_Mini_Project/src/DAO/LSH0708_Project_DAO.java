package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.EMP_DTO;

public class LSH0708_Project_DAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";

	public LSH0708_Project_DAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<EMP_DTO> select() {
		ArrayList<EMP_DTO> list = new ArrayList<EMP_DTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);

			String query = "SELECT empno, ename , deptno , sal  FROM emp WHERE deptno = ( SELECT deptno FROM emp WHERE ename = 'SCOTT') "
					+ "AND sal > ( SELECT sal FROM emp WHERE ename = 'SMITH')";
			System.out.println(query);
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EMP_DTO dto = new EMP_DTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setDeptno(rs.getInt("deptno"));
				dto.setSal(rs.getInt("sal"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	};
	// 김진영

	// 이상현

	// 조이한

	// 강준영

	// 조수빈

}