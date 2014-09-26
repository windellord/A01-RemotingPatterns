package evs2009;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class MetaData implements Serializable {

	private String name;
	private String owner;
	private Date creationDate;
	private String lastModifier;
	private Date lastModifcation;
	private long size;


	public MetaData() {
		super();
	}

	public MetaData(String name, String owner, long size) {
		creationDate = new Date();
		lastModifcation = new Date();
		this.size = size;
		this.name = name;
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastModifcation() {
		return lastModifcation;
	}
	public void setLastModifcation(Date lastModifcation) {
		this.lastModifcation = lastModifcation;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	/**
	 * @return the lastModifier
	 */
	public String getLastModifier() {
		return lastModifier;
	}
	/**
	 * @param lastModifier the lastModifier to set
	 */
	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((lastModifcation == null) ? 0 : lastModifcation.hashCode());
		result = prime * result
				+ ((lastModifier == null) ? 0 : lastModifier.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetaData other = (MetaData) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (lastModifcation == null) {
			if (other.lastModifcation != null)
				return false;
		} else if (!lastModifcation.equals(other.lastModifcation))
			return false;
		if (lastModifier == null) {
			if (other.lastModifier != null)
				return false;
		} else if (!lastModifier.equals(other.lastModifier))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	public static byte[] serialize(MetaData data) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(data);
		oos.flush();
		oos.close();
		return bos.toByteArray();
	}

	public static MetaData unserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois  = new ObjectInputStream(bis);
		MetaData md = (MetaData) ois.readObject();
		ois.close();
		return md;
	}


}
