package univ.sm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by kwonsujeong on 2017. 6. 26..
 */

public class SettingActivity extends PreferenceActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingFragment()).commit();
    }
    public static class SettingFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting);
            setHasOptionsMenu(true);

        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onDetach() {
            super.onDetach();
            PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
        }


        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            Preference connectionPref = findPreference(key);
            if (connectionPref instanceof EditTextPreference) {
                EditTextPreference editPref = (EditTextPreference) connectionPref;
                editPref.setSummary(editPref.getText());
            }
        }
    }
}
