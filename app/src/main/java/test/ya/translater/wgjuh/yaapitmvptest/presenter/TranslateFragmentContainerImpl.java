package test.ya.translater.wgjuh.yaapitmvptest.presenter;

import android.content.Intent;
import android.util.Log;

import test.ya.translater.wgjuh.yaapitmvptest.LeakCanaryApp;
import test.ya.translater.wgjuh.yaapitmvptest.R;
import test.ya.translater.wgjuh.yaapitmvptest.model.IEventBus;
import test.ya.translater.wgjuh.yaapitmvptest.model.IModel;
import test.ya.translater.wgjuh.yaapitmvptest.view.fragments.SettingLangsFragment;
import test.ya.translater.wgjuh.yaapitmvptest.view.fragments.tabs.TransalteView;
import test.ya.translater.wgjuh.yaapitmvptest.view.fragments.translate.InputTranslateFragment;
import test.ya.translater.wgjuh.yaapitmvptest.view.fragments.translate.TranslateListFragment;

import static test.ya.translater.wgjuh.yaapitmvptest.DATA.TAG;


/**
 * Created by wGJUH on 04.04.2017.
 */
// TODO: 06.04.2017 добавить bind, unbind для вьюх срочно !
public class TranslateFragmentContainerImpl extends BasePresenter<TransalteView> {
    private final IModel iModel;
    private final IEventBus iEventBus;
    /**
     * Данный презентер будет являтся презентером для composite view состоящего из трех фрагментов
     */
    public TranslateFragmentContainerImpl(IModel iModel, IEventBus iEventBus) {

        this.iModel = iModel;
        this.iEventBus = iEventBus;
    }

    public void addFragments(InputTranslateFragment inputTranslateFragment, TranslateListFragment translateListFragment) {
        iModel.updateLanguages();
        view.getTranslateFragmentManager()
                .beginTransaction()
                .add(view.getInputFrame().getId(), inputTranslateFragment, inputTranslateFragment.getClass().getName())
                .add(view.getTranslateFrame().getId(), translateListFragment, translateListFragment.getClass().getName())
                .commit();
    }

    public void onChooseFromLanguage(){
        view.getTranslateFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,new SettingLangsFragment())
                .addToBackStack(InputTranslateFragment.class.getName())
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .commit();
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + this.getClass().getName());
    }


}
