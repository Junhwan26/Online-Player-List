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
			// 1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");

			// 2. �����ϱ�
			String dataBase = "test"; // ����
			
			//�����ּ�:port/database
			String url = "jdbc:mysql://*********/" + dataBase
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			//url, user, passwd
			conn = DriverManager.getConnection(url, "*******", "*******");
			System.out.println(ChatColor.GREEN+"DB���� ����");

        }
        catch( ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }
        catch( SQLException e){
            System.out.println("���� " + e);
        }
		
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent Player) {

		String sql = "INSERT INTO OnlinePlayerList VALUES ('"+Player.getPlayer().getName()+"')";
        
		try{
			pstmt = conn.prepareStatement(sql);
	        count = pstmt.executeUpdate();
	        if( count == 0 ){
	            System.out.println("������ �Է� ����");
	        }
	        else{
	            System.out.println("������ �Է� ����");
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
	            System.out.println("������ ���� ����");
	        }
	        else{
	            System.out.println("������ ���� ����");
	            count=0;
	        }
        }
		catch(Exception e) {
			System.out.println("Error : "+e);
		}

	}

}