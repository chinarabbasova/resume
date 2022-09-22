package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.CountryDaoInter;
import entity.Country;
import entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
    private Country getCountry(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
//        String nationality = rs.getString("nationality");

        Country country = new Country(id, name);
        return country;
    }

    @Override
    public List<Country> getAllCountries() {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select c.name,c.id from country c");
            //id-ye gore countryname getirmek: select country.name as country_name, u.name as user_name, u.surname as user_surname from country
            // left join user u on country.id = u.birthplace_id
            // where u.id=5;
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country country = getCountry(rs);
                result.add(country);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
