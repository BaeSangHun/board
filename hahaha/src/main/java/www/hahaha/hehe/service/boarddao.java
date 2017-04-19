package www.hahaha.hehe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import www.hahaha.hehe.service.Board;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
	
	@Repository
	public class boarddao { // Data Access Object
		//Mabatis ����ϱ� ���� 
		@Autowired// sql ���� ���ø��� �ΰ��̱� ������ �������� ������ �ִ� ���� ���Թ޴´�
		private SqlSessionTemplate sqlSessionTemplate;
	    // �ۼ��� �޼���
	    public int updateBoard(Board board) {
	    /*	System.out.println("����ó�� �޼��� ����");
	    	Connection connection = null;
	        PreparedStatement statement = null;
	        int row = 0;
	        try {
	  
	            String sql = "UPDATE board SET board_title=?, board_content=? WHERE board_no=? AND board_pw=?";
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, board.getBoard_title());
	            statement.setString(2, board.getBoard_content());
	            statement.setInt(3, board.getBoard_no());
	            statement.setString(4, board.getBoard_pw());
	            row = statement.executeUpdate();
	            System.out.println("����ó�� �޼��� ���� �Ϸ�");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("���� �߻�");
	        } finally {
	            try {statement.close();} catch(Exception e){}
	            try {connection.close();} catch(Exception e){}
	        } */
	        return sqlSessionTemplate.update("www.hahaha.hehe.service.BoardMapper.update",board);
	    }
	    
	    
	    // �۹�ȣ�� ���н����带 �Է¹޾� �Ѱ��� �Խñ� ����
	    public int deleteBoard(int boardNo, String boardPw) {
	       /* Connection connection = null;
	        PreparedStatement statement = null;
	        int row = 0;
	        try {
	            connection = this.getConnection();
	            String sql = "DELETE FROM board WHERE board_no=? AND board_pw=?";
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, boardNo);
	            statement.setString(2, boardPw);
	            row = statement.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("���� �߻�");
	        } finally {
	            try {statement.close();} catch(Exception e){}
	            try {connection.close();} catch(Exception e){}
	        } */
	    	Board board = new Board();
	    	board.setBoard_no(boardNo);;
	    	board.setBoard_pw(boardPw);
	        return sqlSessionTemplate.delete("www.hahaha.hehe.service.BoardMapper.deleteBoard", board);
	    }
	    
	    // �Ѱ��� �Խñ� ���뺸��
	    public Board getBoard(int boardNo) {
	    	/*Board board = null;
	        Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet rs = null;
	        try {
	            connection = this.getConnection();
	            String sql = "SELECT board_title, board_content, board_user, board_date FROM board WHERE board_no=?";
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, boardNo);
	            rs = statement.executeQuery();
	            if(rs.next()) {
	                board = new Board();
	                board.setBoard_no(boardNo);
	                board.setBoard_title(rs.getString("board_title"));
	                board.setBoard_content(rs.getString("board_content"));
	                board.setBoard_user(rs.getString("board_user"));
	                board.setBoard_date(rs.getString("board_date"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("���� �߻�");
	        } finally {
	            try {rs.close();} catch(Exception e){}
	            try {statement.close();} catch(Exception e){}
	            try {connection.close();} catch(Exception e){}
	        }   */
	        return sqlSessionTemplate.selectOne("www.hahaha.hehe.service.BoardMapper.getBoard", boardNo);
	    }
	    
	    // �Խñ� ���
	    public List<Board> getBoardList(int currentPage, int pagePerRow) {
	       /* ArrayList<Board> list = new ArrayList<Board>();
	        Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet rs = null;
	        try {
	            connection = this.getConnection();
	            String listSql = "SELECT board_no, board_title, board_user, board_date FROM board ORDER BY board_date DESC LIMIT ?, ?";
	            statement = connection.prepareStatement(listSql);
	            statement.setInt(1, (currentPage-1)*pagePerRow); 
	            statement.setInt(2, pagePerRow); 
	            rs = statement.executeQuery();
	            while(rs.next()) {
	            	Board board = new Board();
	            	board.setBoard_no(rs.getInt("board_no"));
	                board.setBoard_title(rs.getString("board_title"));
	                board.setBoard_user(rs.getString("board_user"));
	                board.setBoard_date(rs.getString("board_date"));
	                list.add(board);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("���� �߻�");
	        } finally {
	            try {rs.close();} catch(Exception e){}
	            try {statement.close();} catch(Exception e){}
	            try {connection.close();} catch(Exception e){}
	        }*/
	    	Map<String, Integer> map = new HashMap<String, Integer>();
	        map.put("beginRow", (currentPage-1)*pagePerRow);
	        map.put("pagePerRow", pagePerRow);
	    	
	    	return sqlSessionTemplate.selectList("www.hahaha.hehe.service.BoardMapper.getBoardList", map);
	    }
	    
	    // ��ü �� ī��Ʈ
	    public int getBoardCount() {
	    	return sqlSessionTemplate.selectOne("www.hahaha.hehe.service.BoardMapper.getBoardCount");
	    }
	    
	    // �۾��� �޼���
	    public int insertBoard(Board board) {
	    	
	    	//ù��° �Է� ���� BoardMapper.xml �� insert �� namespace + id�� 
	        return sqlSessionTemplate.insert("www.hahaha.hehe.service.BoardMapper.insertBoard",board);
	    }
	    
	    // �޼��� ���� ����̺� �ε��� Connection�� ���ϴ� �ڵ尡 �ߺ��Ǿ� �ϳ��� �޼���� �����Ͽ���
	   /* public Connection getConnection() throws Exception {
	        Class.forName("com.mysql.jdbc.Driver");
	        String dbUrl = "jdbc:mysql://127.0.0.1:3306/gesi?useUnicode=true&characterEncoding=euckr";
	        String dbUser = "id001";
	        String dbPw = "pw001";
	        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	        System.out.println("DB���Ἲ��");
	        return connection;
	    }*/
	}
	
