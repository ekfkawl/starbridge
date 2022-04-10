package kr.starbridge.web.global.utils;

import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class BeanSubUtils {

    /**
     * null 필드명을 리턴함
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName ->  wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    /**
     * List<T> 맵핑
     * @param source
     * @param mapClass
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T copyPropertiesForList(List<?> source, Class<?> mapClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /** source 사이즈만큼 할당 */
        List<?> target = Collections.nCopies(source.size(), mapClass.getDeclaredConstructor().newInstance());
        /** 리스트 인덱스 복사 */
        for (int i = 0; i < source.size(); i++) {
            BeanUtils.copyProperties(source.get(i), target.get(i));
        }
        return (T) target;
    }
}
