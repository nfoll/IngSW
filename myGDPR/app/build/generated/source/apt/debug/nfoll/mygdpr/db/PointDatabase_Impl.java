package nfoll.mygdpr.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;
import nfoll.mygdpr.dao.PointDao;
import nfoll.mygdpr.dao.PointDao_Impl;

public class PointDatabase_Impl extends PointDatabase {
  private volatile PointDao _pointDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(10) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `elenco_voci` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `priority` TEXT, `date` INTEGER, `dateStr` TEXT, `article` INTEGER NOT NULL, `checked` INTEGER NOT NULL, `intPriority` INTEGER NOT NULL, `personInCharge` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"8e00a2e9881d65385c0bf983499bde8c\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `elenco_voci`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsElencoVoci = new HashMap<String, TableInfo.Column>(9);
        _columnsElencoVoci.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsElencoVoci.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsElencoVoci.put("priority", new TableInfo.Column("priority", "TEXT", false, 0));
        _columnsElencoVoci.put("date", new TableInfo.Column("date", "INTEGER", false, 0));
        _columnsElencoVoci.put("dateStr", new TableInfo.Column("dateStr", "TEXT", false, 0));
        _columnsElencoVoci.put("article", new TableInfo.Column("article", "INTEGER", true, 0));
        _columnsElencoVoci.put("checked", new TableInfo.Column("checked", "INTEGER", true, 0));
        _columnsElencoVoci.put("intPriority", new TableInfo.Column("intPriority", "INTEGER", true, 0));
        _columnsElencoVoci.put("personInCharge", new TableInfo.Column("personInCharge", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysElencoVoci = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesElencoVoci = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoElencoVoci = new TableInfo("elenco_voci", _columnsElencoVoci, _foreignKeysElencoVoci, _indicesElencoVoci);
        final TableInfo _existingElencoVoci = TableInfo.read(_db, "elenco_voci");
        if (! _infoElencoVoci.equals(_existingElencoVoci)) {
          throw new IllegalStateException("Migration didn't properly handle elenco_voci(nfoll.mygdpr.data.Point).\n"
                  + " Expected:\n" + _infoElencoVoci + "\n"
                  + " Found:\n" + _existingElencoVoci);
        }
      }
    }, "8e00a2e9881d65385c0bf983499bde8c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "elenco_voci");
  }

  @Override
  public PointDao getPointDao() {
    if (_pointDao != null) {
      return _pointDao;
    } else {
      synchronized(this) {
        if(_pointDao == null) {
          _pointDao = new PointDao_Impl(this);
        }
        return _pointDao;
      }
    }
  }
}
