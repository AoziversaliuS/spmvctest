package oz.web.dao;

import java.util.List;

import oz.web.pojo.Player;

public interface IPlayerDao {
	
	public void add(Player p);
	public void update(Player p);
	public void delete(Player p);
	public Player get(int id);
	public List<Player> getPlayers();

}
