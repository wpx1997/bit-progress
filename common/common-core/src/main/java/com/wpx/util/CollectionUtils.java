package com.wpx.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

import static java.util.stream.Collectors.*;

/**
 * @author wpx
 * Created on 2020/11/18 16:17
 * @desc: 集合工具类
 */
public class CollectionUtils {

    /**
     * 极值类型，默认情况下选择最大值
     */
    public enum ExtremeType {

        /**
         * key重复时取最大
         */
        MAX,

        /**
         * key重复时取最小
         */
        MIN,

    }

    /**
     * 排序方式枚举，默认情况下使用降序
     */
    public enum OrderType {

        /**
         * key重复时取最大
         */
        DESC,

        /**
         * key重复时取最小
         */
        ASC,

    }

    public static <T, R> Map<T, R> emptyMap() {
        return new HashMap<>(16);
    }

    public static <T> List<T> emptyList() {
        return new ArrayList<>();
    }

    public static <T> Set<T> emptySet() {
        return new HashSet<>();
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param map
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection 判断的集合
     * @return boolean true：不为空，false：为空
     */
    public static boolean nonEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map是否不为空
     *
     * @param map 判断的集合
     * @return boolean true：不为空，false：为空
     */
    public static boolean nonEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 集合是否包含元素
     *
     * @param collection
     * @param obj
     */
    public static boolean contains(Collection<?> collection, Object obj) {
        return nonEmpty(collection) && collection.contains(obj);
    }

    /**
     * 对collection分组
     *
     * @param collection    需要分组的集合
     * @param groupFunction 分组的function
     * @return Map<R, . List < C>>
     */
    private static <T, R> Map<R, List<T>> groupCollection(Collection<T> collection, Function<T, R> groupFunction) {
        if (isEmpty(collection)) {
            return emptyMap();
        }
        return collection.stream().collect(groupingBy(groupFunction));
    }

    /**
     * 对集合分组后的元素取某个字段
     *
     * @param collection      需要分组的集合
     * @param groupFunction   分组的function
     * @param mappingFunction 分组后的集合取某个字段的function
     * @return Map<R, . List < C>>
     */
    public static <T, R, C> Map<R, List<C>> groupByCollectionMapping(Collection<T> collection,
                                                                     Function<T, R> groupFunction,
                                                                     Function<T, C> mappingFunction) {
        if (isEmpty(collection)) {
            return emptyMap();
        }
        return collection.stream().collect(groupingBy(groupFunction, mapping(mappingFunction, toList())));
    }

    /**
     * 将集合内的元素类型转换，并将集合的类型转换为list
     *
     * @param collection 传入的集合
     * @param function   <T, C> T传入类型 C 返回类型
     * @return List<C> 转换后的list
     */
    public static <T, C> List<C> conversionList(Collection<T> collection, Function<T, C> function) {
        return mapList(collection, function);
    }

    /**
     * 将集合内的元素类型转换，并将集合的类型转换为set
     *
     * @param collection 传入的list
     * @param function   <T, C> T传入类型 C 返回类型
     * @return Set<C> 转换后的set
     */
    public static <T, C> Set<C> conversionSet(Collection<T> collection, Function<T, C> function) {
        return mapSet(collection, function);
    }

    /**
     * 筛选集合后，返回list
     *
     * @param collection 传入的list
     * @param predicate  <T> T传入类型
     * @return List<T>
     */
    public static <T> List<T> filterList(Collection<T> collection, Predicate<? super T> predicate) {
        if (isEmpty(collection)) {
            return emptyList();
        }
        return collection.stream().filter(predicate).collect(toList());
    }

    /**
     * 筛选集合后，返回set
     *
     * @param collection 传入的collection
     * @param predicate  <T> T传入类型
     * @return Set<T>
     */
    public static <T> Set<T> filterSet(Collection<T> collection, Predicate<? super T> predicate) {
        if (isEmpty(collection)) {
            return emptySet();
        }
        return collection.stream().filter(predicate).collect(toSet());
    }

    /**
     * collection转换为map
     * key重复情况下，比较器的类型默认为MAX最大值
     * 转换后map的value类型默认为T，即传入的list类型
     *
     * @param collection         传入的collection
     * @param keyFunction        获取map的key的function <T, R> T传入的类型，R转换后map的key类型
     * @param comparableFunction key重复的情况下获取比较器的function <T, U> T传入的类型，U生成比较器的类型
     * @return Map<R, T>
     */
    public static <T, R, U extends Comparable<U>> Map<R, T> collectionToMap(Collection<T> collection,
                                                                            Function<T, R> keyFunction,
                                                                            Function<T, U> comparableFunction) {
        return collectionToMap(collection, keyFunction, comparableFunction, t -> t, ExtremeType.MAX);
    }

    /**
     * collection 转换map
     * key重复情况下，比较器的类型默认为MAX最大值
     *
     * @param collection         需要转换的collection
     * @param keyFunction        获取map的key的function <T, R> T传入的类型，R转换后map的key类型
     * @param comparableFunction key重复的情况下获取比较器的function <T, U> T传入的类型，U生成比较器的类型
     * @param valueFunction      valueFunction  获取map的value的function <T, C> T传入类型，C转换后map的value类型
     * @return Map<R, C>
     */
    public static <T, R, U extends Comparable<U>, C> Map<R, C> collectionToMap(Collection<T> collection,
                                                                               Function<T, R> keyFunction,
                                                                               Function<T, U> comparableFunction,
                                                                               Function<T, C> valueFunction) {
        return collectionToMap(collection, keyFunction, comparableFunction, valueFunction, ExtremeType.MAX);
    }

    /**
     * 将collection 转换为 map
     * 转换后map的value类型默认为T，即传入的list类型
     *
     * @param collection         需要转换的collection
     * @param keyFunction        获取map的key的function <T, R> T传入的类型，R转换后map的key类型
     * @param comparableFunction key重复的情况下获取比较器的function <T, U> T传入的类型，U生成比较器的类型
     * @param type               key重复情况下，比较器的类型，MAX最大值或MIN最小值
     * @return Map<R, T> 转换后的map
     */
    public static <T, R, U extends Comparable<U>> Map<R, T> collectionToMap(Collection<T> collection,
                                                                            Function<T, R> keyFunction,
                                                                            Function<T, U> comparableFunction,
                                                                            ExtremeType type) {
        return collectionToMap(collection, keyFunction, comparableFunction, t -> t, type);
    }

    /**
     * collection 转换为map
     *
     * @param collection         需要转换的collection
     * @param keyFunction        获取map的key的function <T, R> T传入的类型，R转换后map的key类型
     * @param comparableFunction key重复的情况下获取比较器的function <T, U> T传入的类型，U生成比较器的类型
     * @param valueFunction      获取map的value的function <T, C> T传入类型，C转换后map的value类型
     * @param type               key重复情况下，比较器的类型，MAX最大值或MIN最小值，默认为MAX
     * @return Map<R, C>
     */
    public static <T, C, R, U extends Comparable<U>> Map<R, C> collectionToMap(Collection<T> collection,
                                                                               Function<T, R> keyFunction,
                                                                               Function<T, U> comparableFunction,
                                                                               Function<T, C> valueFunction,
                                                                               ExtremeType type) {
        if (isEmpty(collection)) {
            return emptyMap();
        }
        return checkParamUnRepeat(collection, keyFunction) ? simpleToMap(collection, keyFunction, valueFunction)
                : groupingToMap(collection, keyFunction, comparableFunction, valueFunction, type);
    }

    /**
     * 检查集合中元素的某一个参数是否有重复
     *
     * @param collection
     * @param function
     * @param <T>
     * @param <R>
     * @return true：无重复，false：重复
     */
    public static <T, R> boolean checkParamUnRepeat(Collection<T> collection, Function<T, R> function) {
        return isEmpty(collection) || (conversionSet(collection, function).size() == collection.size());
    }

    /**
     * collection 转换为 map
     * key不重复的情况将collection转换为map的方法
     * 为避免空指针，对集合元素进行非空筛选
     *
     * @param collection    需要转换的collection
     * @param keyFunction   获取map的key的function <T, R> T传入的类型，R转换后map的key类型
     * @param valueFunction 获取map的value的function <T, C> T传入类型，C转换后map的value类型
     * @return Map<R, C>
     */
    private static <T, C, R> Map<R, C> simpleToMap(Collection<T> collection,
                                                   Function<T, R> keyFunction,
                                                   Function<T, C> valueFunction) {
        return collection.stream().filter(Objects::nonNull).collect(toMap(keyFunction, valueFunction));
    }

    /**
     * collection 转换为 map
     * key重复的情况下将list转换为map的方法
     * 为避免对分组后的元素进行mapping时空指针，在分组前对集合进行筛选
     *
     * @param collection         需要转换的collection
     * @param keyFunction        获取map的key的function <T, R> T传入的类型，R转换后map的key类型
     * @param comparableFunction key重复的情况下获取构造器的function <T, U> T传入的类型，U生成比较器的类型
     * @param valueFunction      获取map的value的function <T, C> T传入类型，C转换后map的value类型
     * @param type               key重复情况下，比较器的类型，MAX最大值或MIN最小值，默认为MAX
     * @return Map<R, C>
     */
    private static <T, C, R, U extends Comparable<U>> Map<R, C> groupingToMap(Collection<T> collection,
                                                                              Function<T, R> keyFunction,
                                                                              Function<T, U> comparableFunction,
                                                                              Function<T, C> valueFunction,
                                                                              ExtremeType type) {
        return collection.stream().filter(Objects::nonNull).collect(groupingBy(keyFunction, collectingAndThen(
                collectingAndThen(comparable(comparableFunction, type), Optional::get), valueFunction)));
    }

    /**
     * 获取集合的第一个元素
     * 不排序
     *
     * @param list 集合
     * @return Optional<T>
     */
    public static <T> Optional<T> limitOne(List<T> list) {
        return limitOne(list, 0);
    }

    /**
     * 获取跳过指定个数元素后的第一个元素
     * 不排序
     *
     * @param list 集合
     * @param skip 跳过元素的个数
     * @return Optional<T>
     */
    public static <T> Optional<T> limitOne(List<T> list, long skip) {
        if (isEmpty(list)) {
            return Optional.empty();
        }
        return list.stream().skip(skip).findFirst();
    }

    /**
     * 获取指定方式排序后的第一个元素
     * 默认不跳过元素
     * 默认降序排序
     *
     * @param list     传入的集合
     * @param function 排序元素
     * @return Optional<T>
     */
    public static <T, C extends Comparable<C>> Optional<T> limitOne(List<T> list, Function<T, C> function) {
        return limitOne(list, function, 0, OrderType.DESC);
    }

    /**
     * 获取指定方式排序后  跳过一定数量元素后  的第一个元素
     * 默认降序排序
     *
     * @param list     传入的集合
     * @param function 排序元素
     * @param skip     跳过元素个数
     * @return Optional<T>
     */
    public static <T, C extends Comparable<C>> Optional<T> limitOne(List<T> list,
                                                                    Function<T, C> function,
                                                                    long skip) {
        return limitOne(list, function, skip, OrderType.DESC);
    }

    /**
     * 获取 通过指定方式，指定元素排序后 的第一个元素
     *
     * @param list      传入集合
     * @param function  排序元素
     * @param orderType 排序方式
     * @return Optional<T>
     */
    public static <T, C extends Comparable<C>> Optional<T> limitOne(List<T> list, Function<T, C> function,
                                                                    OrderType orderType) {
        return limitOne(list, function, 0, orderType);
    }

    /**
     * 获取 通过指定方式，指定元素排序后 跳过一定数量元素后 的第一个元素
     *
     * @param list      传入集合
     * @param function  排序方法
     * @param skip      跳过元素个数
     * @param orderType 排序方式
     * @return Optional<T>
     */
    public static <T, C extends Comparable<C>> Optional<T> limitOne(List<T> list,
                                                                    Function<T, C> function,
                                                                    long skip,
                                                                    OrderType orderType) {
        if (isEmpty(list)) {
            return Optional.empty();
        }
        return list.stream().sorted(getComparator(function, orderType)).skip(skip).findFirst();
    }

    /**
     * 截取 list的前 {limit} 个元素
     * 不排序，不跳过
     * 返回list
     *
     * @param collection 传入的集合
     * @param limit      获取元素的个数
     * @return List<T>
     */
    public static <T> List<T> limitList(Collection<T> collection, long limit) {
        return limitList(collection, 0, limit);
    }

    /**
     * 截取集合的前 {limit} 个元素
     * 不排序
     * 返回list
     *
     * @param collection 传入的集合
     * @param skip       跳过元素的个数
     * @param limit      获取元素的个数
     * @return List<T>
     */
    public static <T> List<T> limitList(Collection<T> collection, long skip, long limit) {
        if (isEmpty(collection)) {
            return emptyList();
        }
        return collection.stream().skip(skip).limit(limit).collect(toList());
    }

    /**
     * 获取 指定元素排序后的前 {limit} 个元素
     * 默认不跳过
     * 默认降序排序
     * 返回list
     *
     * @param list     需要截取的list
     * @param function 排序元素
     * @param limit    截取元素个数
     * @return List<T>
     */
    public static <T, C extends Comparable<C>> List<T> limitList(List<T> list, Function<T, C> function, long limit) {
        return limitList(list, function, 0, limit, OrderType.DESC);
    }

    /**
     * 获取 指定元素，指定方式排序后的前 {limit} 个元素
     * 默认不跳过
     *
     * @param list      需要截取的list
     * @param function  排序元素
     * @param limit     截取元素个数
     * @param orderType 排序方式
     * @return List<T>
     */
    public static <T, C extends Comparable<C>> List<T> limitList(List<T> list,
                                                                 Function<T, C> function,
                                                                 long limit,
                                                                 OrderType orderType) {
        return limitList(list, function, 0, limit, orderType);
    }

    /**
     * 通过指定元素，指定方式 排序后，跳过skip个元素后，截取list中的前limit个元素
     *
     * @param list      需要截取的list
     * @param function  排序元素
     * @param skip      跳过元素个数
     * @param limit     截取元素个数
     * @param orderType 排序方式
     * @return List<T>
     */
    public static <T, C extends Comparable<C>> List<T> limitList(List<T> list,
                                                                 Function<T, C> function,
                                                                 long skip,
                                                                 long limit,
                                                                 OrderType orderType) {
        if (isEmpty(list)) {
            return emptyList();
        }
        return list.stream().sorted(getComparator(function, orderType)).skip(skip).limit(limit).collect(toList());
    }

    /**
     * 截取 跳过 {skip} 个元素后的所有元素
     * 不排序
     *
     * @param list 需要截取的list
     * @param skip 跳过元素个数
     * @return List<T>
     */
    public static <T> List<T> skipList(List<T> list, long skip) {
        return list.stream().skip(skip).collect(toList());
    }

    /**
     * 截取 指定元素排序后 跳过 {skip} 个元素后的所有元素
     * 默认降序排序
     *
     * @param list     需要截取的list
     * @param function 排序元素
     * @param skip     跳过元素个数
     * @return List<T>
     */
    public static <T, C extends Comparable<C>> List<T> skipList(List<T> list, Function<T, C> function, long skip) {
        return skipList(list, function, skip, OrderType.DESC);
    }

    /**
     * 获取 指定元素，指定方式排序后 跳过{skip}个元素后的所有元素
     *
     * @param list      需要截取的list
     * @param function  排序元素
     * @param skip      跳过的元素个数
     * @param orderType 排序方式
     * @return List<T>
     */
    public static <T, C extends Comparable<C>> List<T> skipList(List<T> list,
                                                                Function<T, C> function,
                                                                long skip,
                                                                OrderType orderType) {
        if (isEmpty(list)) {
            return emptyList();
        }
        return list.stream().sorted(getComparator(function, orderType)).skip(skip).collect(toList());
    }

    /**
     * 获取Comparator
     *
     * @param function 生成比较器的function
     * @return Comparator<T> T类型的比较器
     */
    private static <T, C extends Comparable<C>> Comparator<T> getComparator(Function<T, C> function) {
        return Comparator.comparing(function);
    }

    /**
     * 根据ComparableType获取Comparator
     *
     * @param function 生成比较器的function
     * @param type     排序方式，ASC：升序，DESC降序
     * @return Comparator<T> T类型的比较器
     */
    private static <T, C extends Comparable<C>> Comparator<T> getComparator(Function<T, C> function,
                                                                            OrderType type) {
        Comparator<T> comparator = getComparator(function);
        return OrderType.ASC == type ? comparator : comparator.reversed();
    }

    /**
     * 获取对应比较类型的比较器
     *
     * @param function
     * @param type
     * @param <T>
     * @param <C>
     * @return
     */
    private static <T, C extends Comparable<C>> Collector<T, ?, Optional<T>> comparable(Function<T, C> function,
                                                                                        ExtremeType type) {
        return ExtremeType.MIN == type ? minBy(getComparator(function)) : maxBy(getComparator(function));
    }

    /**
     * 对集合进行map操作后返回list
     *
     * @param collection
     * @param function
     * @param <T>
     * @param <R>
     * @return List<R>
     */
    private static <T, R> List<R> mapList(Collection<T> collection, Function<T, R> function) {
        if (isEmpty(collection)) {
            return emptyList();
        }
        return collection.stream().map(function).collect(toList());
    }

    /**
     * 对集合进行map操作后返回set
     *
     * @param collection
     * @param function
     * @param <T>
     * @param <R>
     * @return Set<R>
     */
    private static <T, R> Set<R> mapSet(Collection<T> collection, Function<T, R> function) {
        if (isEmpty(collection)) {
            return emptySet();
        }
        return collection.stream().map(function).collect(toSet());
    }

}
