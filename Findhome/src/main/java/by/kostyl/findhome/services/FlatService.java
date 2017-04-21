package by.kostyl.findhome.services;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.kostyl.findhome.dal.CrudDao;
import by.kostyl.findhome.entity.offers.Comfort;
import by.kostyl.findhome.entity.offers.Flat;
import by.kostyl.findhome.entity.offers.Room;
import by.kostyl.findhome.entity.offers.RoomPhoto;
import by.kostyl.findhome.entity.user.User;
import by.kostyl.findhome.json.models.FlatModel;
import by.kostyl.findhome.json.models.RoomModel;



@Service
public class FlatService {
	@Autowired
	CrudDao cruddao;

	public void saveFlat(FlatModel flat,Long long1) {
		Flat DAOflat=new Flat();
		
		Set<Comfort> comfortSet=new HashSet();
		Set<Room> roomsSet=new HashSet();
		for(RoomModel model:flat.getRooms()){
			Room room=new Room();
			room.setFlat(DAOflat);
			List<RoomPhoto> list=new ArrayList();
			for(String img:model.getImgs()){
				RoomPhoto roomPhoto=new RoomPhoto();
				roomPhoto.setSource(img.getBytes());
				roomPhoto.setRoom(room);
				list.add(roomPhoto);
			}
			room.setPhotos(list);
			room.setName(model.getName());
			roomsSet.add(room);
		}
		for(String comfort:flat.getComforts()){
			Comfort c=new Comfort();
			c.setName(comfort);
			c.setFlat(DAOflat);
			comfortSet.add(c);
		}
		User user=cruddao.find(User.class,long1 );
		System.out.println(user.getPassword());
		DAOflat.setUser(user);
		DAOflat.setAdress(flat.getAdress());
		DAOflat.setCity(flat.getCity());
		DAOflat.setDescription(flat.getDescription());
		DAOflat.setRegion(flat.getRegion());
		DAOflat.setRooms(roomsSet);
		DAOflat.setComforts(comfortSet);
		cruddao.merge(DAOflat);
	}

	public void deleteFlat(long flatID) {
		cruddao.delete(Flat.class, flatID);
		
	}

}
