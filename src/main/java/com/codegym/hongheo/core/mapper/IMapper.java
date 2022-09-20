package com.codegym.hongheo.core.mapper;

public interface IMapper<E,D> {
    E convertToE(D d);
    D convertToD(E e);
}
