package org.junhwan.main.Events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;



public class EventsClass implements Listener {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	int count=0;
	
	public String prefix = (ChatColor.GREEN + "OPL>> ");

	public EventsClass() {
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String dataBase = "test"; // 디비명
			
			//서버주소:port/database
			String url = "jdbc:mysql://*********/" + dataBase
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			//url, user, passwd
			conn = DriverManager.getConnection(url, "*******", "*******");
			System.out.println(ChatColor.GREEN+"DB연동 성공");

        }
        catch( ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch( SQLException e){
            System.out.println("에러 " + e);
        }
		
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent Player) {

		String sql = "INSERT INTO OnlinePlayerList VALUES ('"+Player.getPlayer().getName()+"')";
        
		try{
			pstmt = conn.prepareStatement(sql);
	        count = pstmt.executeUpdate();
	        if( count == 0 ){
	            System.out.println("데이터 입력 실패");
	        }
	        else{
	            System.out.println("데이터 입력 성공");
	            count=0;
	        }
        }
		catch(Exception e) {
			System.out.println("Error : "+e);
		}

	}
	
	@EventHandler
	void onLeft(PlayerQuitEvent Player) {
	String sql = "delete from OnlinePlayerList where username='"+Player.getPlayer().getName()+"'";
        
		try{
			pstmt = conn.prepareStatement(sql);
	        count = pstmt.executeUpdate();
	        if( count == 0 ){
	            System.out.println("데이터 삭제 실패");
	        }
	        else{
	            System.out.println("데이터 삭제 성공");
	            count=0;
	        }
        }
		catch(Exception e) {
			System.out.println("Error : "+e);
		}

	}

}