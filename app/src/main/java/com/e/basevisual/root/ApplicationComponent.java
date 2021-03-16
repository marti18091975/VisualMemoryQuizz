package com.e.basevisual.root;

import com.e.basevisual.Activity2.Activity2Module;
import com.e.basevisual.Activity1.Activity1Module;
import com.e.basevisual.Activity2.Activity2View;
import com.e.basevisual.Activity3.Activity3Module;
import com.e.basevisual.Activity3.Activity3View;
import com.e.basevisual.Activity4.Activity4Module;
import com.e.basevisual.Activity4.Activity4View;
import com.e.basevisual.utils.SharedPrefsUtil;
import com.e.basevisual.Activity1.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, Activity2Module.class, Activity1Module.class, Activity3Module.class, Activity4Module.class})
public interface ApplicationComponent {
    void inject(MainActivity target);
    void inject(Activity2View target);
    void inject(Activity3View target);
    void inject(Activity4View target);
    SharedPrefsUtil getSharedPrefsUtil();

}
