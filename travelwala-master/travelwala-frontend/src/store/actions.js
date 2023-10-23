import {ADD_ADULT, ADD_CHILD, ADD_INFANT, REMOVE_ADULT, REMOVE_CHILD, REMOVE_INFANT} from "../redux/constants";

export const addAdult = payload => ({
    type: ADD_ADULT,
    payload
})

export const addChild = payload => ({
    type: ADD_CHILD,
    payload
})

export const addInfant = payload => ({
    type: ADD_INFANT,
    payload
})

export const removeAdult = payload => ({
    type: REMOVE_ADULT,
    payload
})

export const removeChild = payload => ({
    type: REMOVE_CHILD,
    payload
})

export const removeInfant = payload => ({
    type: REMOVE_INFANT,
    payload
})
