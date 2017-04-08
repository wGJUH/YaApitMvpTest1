package test.ya.translater.wgjuh.yaapitmvptest.model;


import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import test.ya.translater.wgjuh.yaapitmvptest.DATA;
import test.ya.translater.wgjuh.yaapitmvptest.Event;
import test.ya.translater.wgjuh.yaapitmvptest.model.data.LangsDirsModelPojo;
import test.ya.translater.wgjuh.yaapitmvptest.model.data.TranslatePojo;
import test.ya.translater.wgjuh.yaapitmvptest.model.dict.DictDTO;
import test.ya.translater.wgjuh.yaapitmvptest.model.network.YandexDictionaryApiInterface;
import test.ya.translater.wgjuh.yaapitmvptest.model.network.YandexDictionaryApiModule;
import test.ya.translater.wgjuh.yaapitmvptest.model.network.YandexTranslateApiInterface;
import test.ya.translater.wgjuh.yaapitmvptest.model.network.YandexTranslateApiModule;


import rx.Observable;

/**
 * Created by wGJUH on 04.04.2017.
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;
    private final YandexTranslateApiInterface yandexTranslateApiInterface = YandexTranslateApiModule.getYandexTranslateApiInterface();
    private final YandexDictionaryApiInterface yandexDictionaryApiInterface = YandexDictionaryApiModule.getYandexDictionaryApiInterface();
    private Observable<TranslatePojo> c;
    // TODO: 07.04.2017 Статичные переменные это плохо 
    private static PublishSubject<Event> eventBus = PublishSubject.create();
    // TODO: 07.04.2017 Узнать стоит ли так использовать наблюдатели

    public ModelImpl() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());

    }

    @Override
    public Observable<LangsDirsModelPojo> getLangsDirsForLanguage(String language) {
        return yandexTranslateApiInterface.getLangs(DATA.API_KEY, language)
                .compose(applySchedulers());
    }

    @Override
    public Observable<TranslatePojo> getTranslateForLanguage(String target, String language) {
        return yandexTranslateApiInterface
                .translateForLanguage(DATA.API_KEY, target, language)
                //.delay(10000, TimeUnit.MILLISECONDS)
                .compose(applySchedulers());
    }

    @Override
    public Observable<DictDTO> getDicTionaryTranslateForLanguage(String target, String language) {
        return yandexDictionaryApiInterface
                .translateForLanguage(DATA.DICT_API_KEY, language, target)
                //.delay(10000, TimeUnit.MILLISECONDS)
                .compose(applySchedulers());
    }


    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

    public static PublishSubject<Event> getEventBus() {
        return eventBus;
    }

}