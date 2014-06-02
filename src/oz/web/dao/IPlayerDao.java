package oz.web.dao;

import oz.web.pojo.Player;

public interface IPlayerDao {
	
	public void add(Player p);
	public void update(Player p);
	public void delete(Player p);
	public Player get(int id);

}
