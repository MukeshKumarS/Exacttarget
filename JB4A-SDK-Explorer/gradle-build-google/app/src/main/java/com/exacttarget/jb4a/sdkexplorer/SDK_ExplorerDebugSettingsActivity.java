/**
 * Copyright (c) 2015 Salesforce Marketing Cloud.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.exacttarget.jb4a.sdkexplorer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.exacttarget.etpushsdk.ETPush;
import com.exacttarget.etpushsdk.util.ETLogger;
import com.exacttarget.jb4a.sdkexplorer.utils.Utils;

import java.io.File;

/**
 * SDK_ExplorerDebugSettingsActivity is a settings activity to manage debug options within the JB4A SDK Explorer.
 * <p/>
 * This activity extends PreferenceActivity to provide the debug settings to debug issues or trace code execution.
 * <p/>
 * Settings:
 * <p/>
 * 1) Turn Debug On.
 * In the production version of the JB4A SDK Explorer, the default logging level is WARN.  This setting will turn
 * logging to DEBUG.
 * <p/>
 * 2) Capture Logcat
 * After running some tests, you may want to see how to see the debug info is produced in the logcat.  This will
 * be especially useful if you send to a customer to test.  This setting requires the following permissions in the Manifest:
 * <uses-permission android:name="android.permission.READ_LOGS" />  // to create Logcat file
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/> // to create logCat file
 *
 * @author pvandyk
 */

public class SDK_ExplorerDebugSettingsActivity extends BasePreferenceActivity {

    private static final int currentPage = CONSTS.DEBUG_SETTINGS_ACTIVITY;
    private static final String TAG = Utils.formatTag(SDK_ExplorerDebugSettingsActivity.class.getSimpleName()) ;
    private SharedPreferences sp;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = PreferenceManager.getDefaultSharedPreferences(SDK_ExplorerApp.context());

        addPreferencesFromResource(R.xml.debug_prefs);

        prepareDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT >= 11) {
            Utils.setPrefActivityTitle(this, getString(R.string.debug_settings_activity_title));
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Utils.prepareMenu(currentPage, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Boolean result = Utils.selectMenuItem(this, currentPage, item);
        return result != null ? result : super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //
    // Prepare the display of the settings
    //
    @SuppressWarnings("deprecation")
    private void prepareDisplay() {

        //
        // ENABLE DEBUG ON PREFERENCE
        //
        CheckBoxPreference edPref = (CheckBoxPreference) findPreference(CONSTS.KEY_DEBUG_PREF_ENABLE_DEBUG);
        if (ETPush.getLogLevel() <= Log.DEBUG) {
            edPref.setChecked(true);
        } else {
            edPref.setChecked(false);
        }

        edPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference pref, Object newValue) {
                Boolean new_edPref = (Boolean) newValue;

                if (new_edPref) {
                    ETPush.setLogLevel(Log.DEBUG);
                } else {
                    ETPush.setLogLevel(Log.WARN);

                    // stop capturing log
                    ETLogger.getInstance().stopCapture();
                }
                updatePreferencesForKey(CONSTS.KEY_DEBUG_PREF_ENABLE_DEBUG, new_edPref);

                return true;
            }
        });

        //
        // COLLECT LOGCAT PREFERENCE
        //
        Preference clPref = findPreference(CONSTS.KEY_DEBUG_PREF_COLLECT_LOGCAT);

        clPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String[] emailAddresses = {""};

                ETLogger.getInstance().emailLogFile(SDK_ExplorerDebugSettingsActivity.this, emailAddresses, "Android JB4A SDK Explorer Debug Info", "Here is the logfile from the JB4A SDK.");

                return true;
            }
        });

    } //prepareDisplay()

    //
    // updatePreferencesForKey
    //
    // Update the Shared Preferences file for the given key.
    //
    private void updatePreferencesForKey(String key, Boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

}
