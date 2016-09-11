package fi.antti.jee.bean;

public class Lasku {

	String tapahtuma;
	int lasku_id;
	double huone_1_velka, huone_2_velka, huone_3_velka, huone_4_velka,
			velkaa_jaljella, total;

	boolean huone_1_maksettu, huone_2_maksettu, huone_3_maksettu,
			huone_4_maksettu;
	
	String huone_1_timestamp, huone_2_timestamp, huone_3_timestamp, huone_4_timestamp;

	public Lasku() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lasku(String tapahtuma, int lasku_id, double huone_1_velka,
			double huone_2_velka, double huone_3_velka, double huone_4_velka,
			double velkaa_jaljella, double total, boolean huone_1_maksettu,
			boolean huone_2_maksettu, boolean huone_3_maksettu,
			boolean huone_4_maksettu) {
		super();
		this.tapahtuma = tapahtuma;
		this.lasku_id = lasku_id;
		this.huone_1_velka = huone_1_velka;
		this.huone_2_velka = huone_2_velka;
		this.huone_3_velka = huone_3_velka;
		this.huone_4_velka = huone_4_velka;
		this.velkaa_jaljella = velkaa_jaljella;
		this.total = total;
		this.huone_1_maksettu = huone_1_maksettu;
		this.huone_2_maksettu = huone_2_maksettu;
		this.huone_3_maksettu = huone_3_maksettu;
		this.huone_4_maksettu = huone_4_maksettu;
	}

	public String getTapahtuma() {
		return tapahtuma;
	}

	public void setTapahtuma(String tapahtuma) {
		this.tapahtuma = tapahtuma;
	}

	public int getLasku_id() {
		return lasku_id;
	}

	public void setLasku_id(int lasku_id) {
		this.lasku_id = lasku_id;
	}

	public double getHuone_1_velka() {
		return huone_1_velka;
	}

	public void setHuone_1_velka(double huone_1_velka) {
		this.huone_1_velka = huone_1_velka;
	}

	public double getHuone_2_velka() {
		return huone_2_velka;
	}

	public void setHuone_2_velka(double huone_2_velka) {
		this.huone_2_velka = huone_2_velka;
	}

	public double getHuone_3_velka() {
		return huone_3_velka;
	}

	public void setHuone_3_velka(double huone_3_velka) {
		this.huone_3_velka = huone_3_velka;
	}

	public double getHuone_4_velka() {
		return huone_4_velka;
	}

	public void setHuone_4_velka(double huone_4_velka) {
		this.huone_4_velka = huone_4_velka;
	}

	public double getVelkaa_jaljella() {
		return velkaa_jaljella;
	}

	public void setVelkaa_jaljella(double velkaa_jaljella) {
		this.velkaa_jaljella = velkaa_jaljella;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isHuone_1_maksettu() {
		return huone_1_maksettu;
	}

	public void setHuone_1_maksettu(boolean huone_1_maksettu) {
		this.huone_1_maksettu = huone_1_maksettu;
	}

	public boolean isHuone_2_maksettu() {
		return huone_2_maksettu;
	}

	public void setHuone_2_maksettu(boolean huone_2_maksettu) {
		this.huone_2_maksettu = huone_2_maksettu;
	}

	public boolean isHuone_3_maksettu() {
		return huone_3_maksettu;
	}

	public void setHuone_3_maksettu(boolean huone_3_maksettu) {
		this.huone_3_maksettu = huone_3_maksettu;
	}

	public boolean isHuone_4_maksettu() {
		return huone_4_maksettu;
	}

	public void setHuone_4_maksettu(boolean huone_4_maksettu) {
		this.huone_4_maksettu = huone_4_maksettu;
	}
	
	

	public String getHuone_1_timestamp() {
		return huone_1_timestamp;
	}

	public void setHuone_1_timestamp(String huone_1_timestamp) {
		this.huone_1_timestamp = huone_1_timestamp;
	}

	public String getHuone_2_timestamp() {
		return huone_2_timestamp;
	}

	public void setHuone_2_timestamp(String huone_2_timestamp) {
		this.huone_2_timestamp = huone_2_timestamp;
	}

	public String getHuone_3_timestamp() {
		return huone_3_timestamp;
	}

	public void setHuone_3_timestamp(String huone_3_timestamp) {
		this.huone_3_timestamp = huone_3_timestamp;
	}

	public String getHuone_4_timestamp() {
		return huone_4_timestamp;
	}

	public void setHuone_4_timestamp(String huone_4_timestamp) {
		this.huone_4_timestamp = huone_4_timestamp;
	}

	@Override
	public String toString() {
		return "Lasku [tapahtuma=" + tapahtuma + ", lasku_id=" + lasku_id
				+ ", huone_1_velka=" + huone_1_velka + ", huone_2_velka="
				+ huone_2_velka + ", huone_3_velka=" + huone_3_velka
				+ ", huone_4_velka=" + huone_4_velka + ", velkaa_jaljella="
				+ velkaa_jaljella + ", total=" + total + ", huone_1_maksettu="
				+ huone_1_maksettu + ", huone_2_maksettu=" + huone_2_maksettu
				+ ", huone_3_maksettu=" + huone_3_maksettu
				+ ", huone_4_maksettu=" + huone_4_maksettu
				+ ", huone_1_timestamp=" + huone_1_timestamp
				+ ", huone_2_timestamp=" + huone_2_timestamp
				+ ", huone_3_timestamp=" + huone_3_timestamp
				+ ", huone_4_timestamp=" + huone_4_timestamp + "]";
	}



}
