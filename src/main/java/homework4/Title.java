package homework4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Title {
    private Integer id;
    private String name;

    private static final String TABLE = "`titles`";

    Title(String name) {
        this.id = null;
        this.name = name;
    }

    private Title(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

    public void save() {
        Title auth = Title.getByName(this.name);
        if(auth != null) {
            id = auth.getId();
        } else {
            try {
                Title.insert(this.name);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            id = Objects.requireNonNull(Title.getByName(this.name)).getId();
        }
    }

    private static int insert(String name) throws SQLException {
        String cols = "`name`";
        String values = "?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);
        return DBQuery.insert(TABLE, cols, values, params);
    }

	public static Title getById(int id) {
        try {
            ResultSet rSet = Title.select(id);
            if (rSet.first()) return new Title(rSet.getInt(1), rSet.getString(2));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Title getByName(String name) {
        try (ResultSet rSet = Title.select(name)) {
            if (rSet.first()) return new Title(rSet.getInt(1), rSet.getString(2));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static ResultSet select(int id) throws SQLException {
        String cols = "`id`, `name`";
        String conds = "`id` = ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        return DBQuery.select(TABLE, cols, conds, params);
    }

    private static ResultSet select(String name) throws SQLException {
        String cols = "`id`, `name`";
        String conds = "`name` LIKE ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);
        return DBQuery.select(TABLE, cols, conds, params);
    }

    public void rename(String name) {
        if(this.id != null) {
            try {
                update(name);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            this.name = name;
        } else {
            System.out.println("No such entry in database");
        }
    }

    private int update(String name) throws SQLException {
        String colVals = "`name` = ?";
        String conds = "`id` = ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);
        params.put(2, this.id);
        return DBQuery.update(TABLE, colVals, conds, params);
    }

    public void remove() {
        try {
            delete();
            this.id = null;
            this.name = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int delete() throws SQLException {
        String conds = "`id` = ?";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, this.id);
        return DBQuery.delete(TABLE, conds, params);
    }

    public static void migrate() {
        DBQuery.create(TABLE, "`name` VARCHAR(100) NOT NULL UNIQUE");
    }

    public static void drop() {
        DBQuery.drop(TABLE);
    }
}