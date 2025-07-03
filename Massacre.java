package pack1;

public class Massacre {
int id ,victims , date;
String title,location, type;
public Massacre(int id, int victims, String title, int date, String location, String type) {
	
	this.id = id;
	this.victims = victims;
	this.title = title;
	this.date = date;
	this.location = location;
	this.type = type;
}

public Massacre() {}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getVictims() {
	return victims;
}

public void setVictims(int victims) {
	this.victims = victims;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public int getDate() {
	return date;
}

public void setDate(int date) {
	this.date = date;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}


}
