package com.marsfood.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 服务层统一返回对象
 *
 * @author zhuhongxin
 * @date 2018/08/26
 */
public class ListResponse<E> implements Serializable {
    /**
     * 下一页的页码
     */
    private int lastOrder;
    /**
     * 总数量
     */
    private int total = 0;
    /**
     * 消息
     */
    private String message;
    /**
     * 状态码
     */
    private ResponseCode code;
    /**
     * false最后一页
     */
    private boolean hasNext = true;
    /**
     * 结果
     */
    private List<E> result = new ArrayList<>();

    public static <E> ListResponse<E> newInstance() {
        return new ListResponse<E>();
    }

    public static <E> ListResponse<E> newInstance(ResponseCode code, String message, List<E> result) {
        ListResponse<E> response = new ListResponse<E>();
        response.setResult(result);
        return response.fill(code, message);
    }

    private ListResponse<E> fill(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ListResponse<E> fill(ResponseCode code, String message, int total, boolean hasNext) {
        this.code = code;
        this.message = message;
        this.total = total;
        this.hasNext = hasNext;
        return this;
    }

    public ListResponse<E> fill(ResponseCode code, String message, List<E> result, int total, boolean hasNext) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.total = total;
        this.hasNext = hasNext;
        return this;
    }

    public ListResponse<E> fill(ResponseCode code, String message, int total, boolean hasNext, int lastOrder) {
        this.code = code;
        this.message = message;
        this.total = total;
        this.hasNext = hasNext;
        this.lastOrder = lastOrder;
        return this;
    }

 /*   public ResponseCode getCode() {
        return code;
    }*/

    public int getCode() {
        return code.getCode();
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(int lastOrder) {
        this.lastOrder = lastOrder;
    }

    public int size() {
        return this.result.size();
    }

    public void setResult(List<E> list) {
        if (list == null) {
            this.result = new ArrayList<>();
        } else {
            this.result = list;
        }
    }

    public List<E> getResult() {
        return result;
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean empty() {
        return this.result.size() == 0;
    }

    public boolean contains(Object o) {
        return this.result.contains(o);
    }

    public Iterator<E> iterator() {
        return this.result.iterator();
    }

    public Object[] toArray() {
        return this.result.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return this.result.toArray(a);
    }

    public boolean add(E e) {
        return this.result.add(e);
    }

    public boolean remove(Object o) {
        return this.result.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return this.result.containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        return this.result.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return this.result.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return this.result.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return this.result.retainAll(c);
    }

    public void clear() {
        this.result.clear();
    }

    public E get(int index) {
        return this.result.get(index);
    }

    public E set(int index, E element) {
        return this.result.set(index, element);
    }

    public void add(int index, E element) {
        this.result.add(index, element);
    }

    public E remove(int index) {
        return this.result.remove(index);
    }

    public int indexOf(Object o) {
        return this.result.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return this.result.lastIndexOf(o);
    }

    public ListIterator<E> listIterator() {
        return this.result.listIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return this.result.listIterator(index);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return this.result.subList(fromIndex, toIndex);
    }

}
