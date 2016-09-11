package fi.antti.jee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fi.antti.jee.bean.Lasku;

/*
 CREATE TABLE lasku(
 lasku_id INT NOT NULL AUTO_INCREMENT,
 tapahtuma VARCHAR(255) NOT NULL,
 huone_1_velka DECIMAL(7,2) NOT NULL,
 huone_1_maksettu BOOLEAN NOT NULL DEFAULT 0,
 huone_2_velka DECIMAL(7,2) NOT NULL,
 huone_2_maksettu BOOLEAN NOT NULL DEFAULT 0,
 huone_3_velka DECIMAL(7,2) NOT NULL,
 huone_3_maksettu BOOLEAN NOT NULL DEFAULT 0,
 huone_4_velka DECIMAL(7,2) NOT NULL,
 huone_4_maksettu BOOLEAN NOT NULL DEFAULT 0,
 velkaa_jaljella DECIMAL(7,2) NOT NULL,
 total DECIMAL(7,2) NOT NULL,
 PRIMARY KEY (lasku_id)
 )ENGINE=InnoDB;
 */

public class Dao {

	private Connection yhdista() throws SQLException, Exception {
		/*
		 * // Vanha DAO yhteys Connection yhteys = null; String username = "x";
		 * String password = "x"; String JDBCAjuri = "org.mariadb.jdbc.Driver";
		 * String url = "jdbc:mariadb://ana.korpisoturit.com:3306/solu";
		 * 
		 * try { Class.forName(JDBCAjuri); // ajurin m‰‰ritys
		 * 
		 * // otetaan yhteys tietokantaan yhteys =
		 * DriverManager.getConnection(url, username, password);
		 * 
		 * } catch (SQLException sqlE) {
		 * System.err.println("Tietokantayhteyden avaaminen ei onnistunut. " +
		 * url + "\n" + sqlE.getMessage() + " " + sqlE.toString() + "\n");
		 * sqlE.printStackTrace(); throw (sqlE); } catch (Exception e) {
		 * System.err.println("TIETOKANTALIITTYN VIRHETILANNE: " +
		 * "JDBC:n omaa tietokanta-ajuria ei loydy.\n\n" + e.getMessage() + " "
		 * + e.toString() + "\n"); e.printStackTrace(); System.out.print("\n");
		 * throw (e); }
		 * 
		 * return yhteys;
		 */

		// Properties tiedostolla yhteys tietokantaan
		Connection yhteys = null;

		try {
			Class.forName(
					DBConnectionProperties.getInstance().getProperty("driver"))
					.newInstance();
			// avataan yhteys
			yhteys = DriverManager.getConnection(DBConnectionProperties
					.getInstance().getProperty("url"), DBConnectionProperties
					.getInstance().getProperty("username"),
					DBConnectionProperties.getInstance()
							.getProperty("password"));

		} catch (SQLException sqlE) {
			System.err.println("Tietokantayhteyden avaaminen ei onnistunut. "
					+ DBConnectionProperties.getInstance().getProperty("url")
					+ "\n" + sqlE.getMessage() + " " + sqlE.toString() + "\n");
			sqlE.printStackTrace();
			throw (sqlE);
		} catch (Exception e) {
			System.err.println("TIETOKANTALIITTYN VIRHETILANNE: "
					+ "JDBC:n omaa tietokanta-ajuria ei loydy.\n\n"
					+ e.getMessage() + " " + e.toString() + "\n");
			e.printStackTrace();
			System.out.print("\n");
			throw (e);
		}

		return yhteys;

	}

	public ArrayList<Lasku> haeKaikkiTiedot() throws SQLException, Exception {
		ArrayList<Lasku> data = null;
		Lasku lasku = null;
		String sql = "SELECT * FROM lasku WHERE isDeleted = 0";
		PreparedStatement preparedStatement = null; // suoritettava SQL-lause
		ResultSet tulokset = null; // SQL-kyselyn tulokset
		Connection conn = null;
		try {
			conn = yhdista();
			if (conn != null) {
				preparedStatement = conn.prepareStatement(sql);
				tulokset = preparedStatement.executeQuery();
				if (tulokset != null) {
					conn.close(); // sulje yhteys kantaan
					if (data == null) {
						data = new ArrayList<Lasku>();
					}
					while (tulokset.next()) {
						try {
							lasku = new Lasku();

							lasku.setLasku_id(tulokset.getInt("lasku_id"));
							lasku.setTapahtuma(tulokset.getString("tapahtuma"));
							lasku.setHuone_1_maksettu(tulokset
									.getBoolean("huone_1_maksettu"));
							lasku.setHuone_1_velka(tulokset
									.getDouble("huone_1_velka"));
							lasku.setHuone_1_timestamp(tulokset
									.getString("huone_1_timestamp"));
							lasku.setHuone_2_maksettu(tulokset
									.getBoolean("huone_2_maksettu"));
							lasku.setHuone_2_velka(tulokset
									.getDouble("huone_2_velka"));
							lasku.setHuone_2_timestamp(tulokset
									.getString("huone_2_timestamp"));
							lasku.setHuone_3_maksettu(tulokset
									.getBoolean("huone_3_maksettu"));
							lasku.setHuone_3_velka(tulokset
									.getDouble("huone_3_velka"));
							lasku.setHuone_3_timestamp(tulokset
									.getString("huone_3_timestamp"));
							lasku.setHuone_4_maksettu(tulokset
									.getBoolean("huone_4_maksettu"));
							lasku.setHuone_4_velka(tulokset
									.getDouble("huone_4_velka"));
							lasku.setHuone_4_timestamp(tulokset
									.getString("huone_4_timestamp"));
							lasku.setVelkaa_jaljella(tulokset
									.getDouble("velkaa_jaljella"));
							lasku.setTotal(tulokset.getDouble("total"));
							data.add(lasku);

						} catch (Exception e) {
							// TODO: handle exception
							System.out.print("hops" + e);

						}

					}
					tulokset.close();
				} else {
					conn.close();
					data = null;
				}
			}

		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.close(); // yhteys poikki
				} catch (Exception e) {
					System.out.println(e);
					throw e;
				}
			}
		}
		return data;
	}

	public void lisaaLasku(Lasku lasku) throws SQLException, Exception {

		String sql = "INSERT INTO lasku(tapahtuma,"
				+ " huone_1_velka, huone_2_velka, huone_3_velka, huone_4_velka,"
				+ " velkaa_jaljella, total) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement;
		Connection conn = null;
		try {
			conn = yhdista();
			if (conn != null) {
				preparedStatement = conn.prepareStatement(sql);

				preparedStatement.setString(1, lasku.getTapahtuma());
				preparedStatement.setDouble(2, lasku.getHuone_1_velka());
				preparedStatement.setDouble(3, lasku.getHuone_2_velka());
				preparedStatement.setDouble(4, lasku.getHuone_3_velka());
				preparedStatement.setDouble(5, lasku.getHuone_4_velka());
				preparedStatement.setDouble(6, lasku.getVelkaa_jaljella());
				preparedStatement.setDouble(7, lasku.getTotal());
				preparedStatement.execute(); // Ajetaan SQL lause

			}

		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.close(); // yhteys poikki
				} catch (Exception e) {
					System.out.println(e);
					throw e;
				}
			}
		}

	}

	public void updateLasku(int id, double sum, int room) throws SQLException,
			Exception {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm");

		String aikaleima = df.format(date);

		String sql = "UPDATE lasku SET huone_" + room + "_velka = ?, "
				+ "huone_" + room + "_maksettu = ? ,"
				+ " velkaa_jaljella = ?, " + "huone_" + room
				+ "_timestamp = ? WHERE lasku_id = ?";
		PreparedStatement preparedStatement;
		Connection conn = null;

		Lasku lasku = haeRivi(id);

		double temp = lasku.getVelkaa_jaljella() - sum;

		try {
			conn = yhdista();
			if (conn != null) {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, 0);
				preparedStatement.setBoolean(2, true);
				preparedStatement.setDouble(3, temp);
				preparedStatement.setString(4, aikaleima);
				preparedStatement.setInt(5, id);
				preparedStatement.execute(); // Ajetaan SQL lause

			}

		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.close(); // yhteys poikki
				} catch (Exception e) {
					System.out.println(e);
					throw e;
				}
			}
		}

	}

	public Lasku haeRivi(int id) throws SQLException, Exception {

		Lasku data = null;
		String sql = "SELECT * FROM lasku WHERE lasku_id = ? ";
		PreparedStatement preparedStatement = null; // suoritettava SQL-lause
		ResultSet tulokset = null; // SQL-kyselyn tulokset
		Connection conn = null;
		try {
			conn = yhdista();
			if (conn != null) {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				tulokset = preparedStatement.executeQuery();
				if (tulokset != null) {
					conn.close(); // sulje yhteys kantaan

					while (tulokset.next()) {
						data = new Lasku();

						data.setLasku_id(tulokset.getInt("lasku_id"));
						data.setTapahtuma(tulokset.getString("tapahtuma"));
						data.setHuone_1_maksettu(tulokset
								.getBoolean("huone_1_maksettu"));
						data.setHuone_1_velka(tulokset
								.getDouble("huone_1_velka"));
						data.setHuone_2_maksettu(tulokset
								.getBoolean("huone_2_maksettu"));
						data.setHuone_2_velka(tulokset
								.getDouble("huone_2_velka"));
						data.setHuone_3_maksettu(tulokset
								.getBoolean("huone_3_maksettu"));
						data.setHuone_3_velka(tulokset
								.getDouble("huone_3_velka"));
						data.setHuone_4_maksettu(tulokset
								.getBoolean("huone_4_maksettu"));
						data.setHuone_4_velka(tulokset
								.getDouble("huone_4_velka"));
						data.setVelkaa_jaljella(tulokset
								.getDouble("velkaa_jaljella"));
						data.setTotal(tulokset.getDouble("total"));
						System.out.println(data);
					}
					tulokset.close();
				} else {
					conn.close();
					data = null;

				}
			}

		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.close(); // yhteys poikki
				} catch (Exception e) {
					System.out.println(e);
					throw e;
				}
			}
		}
		return data;
	}

	public void poistaLasku(int id) throws SQLException, Exception {

		String sql = "UPDATE lasku SET isDeleted = ? WHERE lasku_id = ?";
		PreparedStatement preparedStatement;
		Connection conn = null;


		try {
			conn = yhdista();
			if (conn != null) {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setBoolean(1, true);
				preparedStatement.setInt(2, id);
				preparedStatement.execute(); // Ajetaan SQL lause

			}

		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		} finally {
			if (conn != null && conn.isClosed() == false) {
				try {
					conn.close(); // yhteys poikki
				} catch (Exception e) {
					System.out.println(e);
					throw e;
				}
			}
		}

	}

}