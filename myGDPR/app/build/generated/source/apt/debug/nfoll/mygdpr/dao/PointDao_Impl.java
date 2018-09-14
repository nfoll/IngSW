package nfoll.mygdpr.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nfoll.mygdpr.Converters;
import nfoll.mygdpr.data.Point;

public class PointDao_Impl implements PointDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPoint;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPoint;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfPoint;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PointDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPoint = new EntityInsertionAdapter<Point>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `elenco_voci`(`id`,`title`,`priority`,`date`,`dateStr`,`article`,`checked`,`intPriority`,`personInCharge`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Point value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getPriority() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPriority());
        }
        final Long _tmp;
        _tmp = __converters.dateToTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        if (value.getDateStr() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDateStr());
        }
        stmt.bindLong(6, value.getArticle());
        stmt.bindLong(7, value.getChecked());
        stmt.bindLong(8, value.getIntPriority());
        if (value.getPersonInCharge() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getPersonInCharge());
        }
      }
    };
    this.__deletionAdapterOfPoint = new EntityDeletionOrUpdateAdapter<Point>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `elenco_voci` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Point value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPoint = new EntityDeletionOrUpdateAdapter<Point>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `elenco_voci` SET `id` = ?,`title` = ?,`priority` = ?,`date` = ?,`dateStr` = ?,`article` = ?,`checked` = ?,`intPriority` = ?,`personInCharge` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Point value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getPriority() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPriority());
        }
        final Long _tmp;
        _tmp = __converters.dateToTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        if (value.getDateStr() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDateStr());
        }
        stmt.bindLong(6, value.getArticle());
        stmt.bindLong(7, value.getChecked());
        stmt.bindLong(8, value.getIntPriority());
        if (value.getPersonInCharge() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getPersonInCharge());
        }
        stmt.bindLong(10, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM elenco_voci";
        return _query;
      }
    };
  }

  @Override
  public Long createPoint(Point point) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfPoint.insertAndReturnId(point);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(Point... points) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPoint.insert(points);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePoint(Point point) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfPoint.handle(point);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePoint(Point point) {
    __db.beginTransaction();
    try {
      __updateAdapterOfPoint.handle(point);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public Point findPointById(int id) {
    final String _sql = "SELECT * FROM elenco_voci WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfPriority = _cursor.getColumnIndexOrThrow("priority");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfDateStr = _cursor.getColumnIndexOrThrow("dateStr");
      final int _cursorIndexOfArticle = _cursor.getColumnIndexOrThrow("article");
      final int _cursorIndexOfChecked = _cursor.getColumnIndexOrThrow("checked");
      final int _cursorIndexOfIntPriority = _cursor.getColumnIndexOrThrow("intPriority");
      final int _cursorIndexOfPersonInCharge = _cursor.getColumnIndexOrThrow("personInCharge");
      final Point _result;
      if(_cursor.moveToFirst()) {
        _result = new Point();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _result.setTitle(_tmpTitle);
        final String _tmpPriority;
        _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        _result.setPriority(_tmpPriority);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = __converters.fromTimestamp(_tmp);
        _result.setDate(_tmpDate);
        final String _tmpDateStr;
        _tmpDateStr = _cursor.getString(_cursorIndexOfDateStr);
        _result.setDateStr(_tmpDateStr);
        final int _tmpArticle;
        _tmpArticle = _cursor.getInt(_cursorIndexOfArticle);
        _result.setArticle(_tmpArticle);
        final int _tmpChecked;
        _tmpChecked = _cursor.getInt(_cursorIndexOfChecked);
        _result.setChecked(_tmpChecked);
        final int _tmpIntPriority;
        _tmpIntPriority = _cursor.getInt(_cursorIndexOfIntPriority);
        _result.setIntPriority(_tmpIntPriority);
        final String _tmpPersonInCharge;
        _tmpPersonInCharge = _cursor.getString(_cursorIndexOfPersonInCharge);
        _result.setPersonInCharge(_tmpPersonInCharge);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Point> viewAll() {
    final String _sql = "SELECT * FROM elenco_voci ORDER BY checked ASC, date ASC, intPriority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfPriority = _cursor.getColumnIndexOrThrow("priority");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfDateStr = _cursor.getColumnIndexOrThrow("dateStr");
      final int _cursorIndexOfArticle = _cursor.getColumnIndexOrThrow("article");
      final int _cursorIndexOfChecked = _cursor.getColumnIndexOrThrow("checked");
      final int _cursorIndexOfIntPriority = _cursor.getColumnIndexOrThrow("intPriority");
      final int _cursorIndexOfPersonInCharge = _cursor.getColumnIndexOrThrow("personInCharge");
      final List<Point> _result = new ArrayList<Point>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Point _item;
        _item = new Point();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpPriority;
        _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
        _item.setPriority(_tmpPriority);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = __converters.fromTimestamp(_tmp);
        _item.setDate(_tmpDate);
        final String _tmpDateStr;
        _tmpDateStr = _cursor.getString(_cursorIndexOfDateStr);
        _item.setDateStr(_tmpDateStr);
        final int _tmpArticle;
        _tmpArticle = _cursor.getInt(_cursorIndexOfArticle);
        _item.setArticle(_tmpArticle);
        final int _tmpChecked;
        _tmpChecked = _cursor.getInt(_cursorIndexOfChecked);
        _item.setChecked(_tmpChecked);
        final int _tmpIntPriority;
        _tmpIntPriority = _cursor.getInt(_cursorIndexOfIntPriority);
        _item.setIntPriority(_tmpIntPriority);
        final String _tmpPersonInCharge;
        _tmpPersonInCharge = _cursor.getString(_cursorIndexOfPersonInCharge);
        _item.setPersonInCharge(_tmpPersonInCharge);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int countUnChecked() {
    final String _sql = "SELECT COUNT (*) FROM elenco_voci WHERE checked = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
