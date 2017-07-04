package com.abnormallydriven.architecturecomponentspost.common.data.migrations;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;

public class Migration1To2 extends Migration {

    public Migration1To2(int startVersion, int endVersion) {
        super(startVersion, endVersion);
    }

    @Override
    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("ALTER TABLE measurements ADD COLUMN weight REAL");
    }
}
