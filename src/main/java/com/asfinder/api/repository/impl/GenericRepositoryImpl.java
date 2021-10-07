package com.asfinder.api.repository.impl;

import com.asfinder.api.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericRepositoryImpl<T, ID extends Serializable>
        implements GenericRepository <T, ID>{

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> type;

    @SuppressWarnings("unchecked")
    public GenericRepositoryImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(T entity) {
        try {
            this.entityManager.persist(entity);
            this.entityManager.flush();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(Object id) {
        try {
            T t = this.entityManager.find(type, id);
            this.entityManager.remove(t);
            this.entityManager.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try{
                T t = this.entityManager.find(type, id);
                this.entityManager.remove(t);
                this.entityManager.flush();
                return true;
            }catch(Exception e1){
                e1.printStackTrace();
                return false;
            }
        }
    }

    public T update(T t) {
        try {
            this.entityManager.merge(t);
            this.entityManager.flush();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T findById(Integer id) {
        try {
            T d = this.entityManager.find(type, id);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(){
        return this.entityManager.createQuery("from " + type.getSimpleName())
                .getResultList();
    }
}
