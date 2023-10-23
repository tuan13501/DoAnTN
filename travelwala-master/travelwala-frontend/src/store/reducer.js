import {ADD_ADULT, ADD_CHILD, ADD_INFANT, REMOVE_ADULT, REMOVE_CHILD, REMOVE_INFANT} from '../redux/constants'

const passengerState = {
    adult: 1,
    child: 0,
    infant: 0
}

function reducer(state, action) {
    switch (action.type) {
        case ADD_ADULT:
            return {
                ...state,
                adult: state.adult + 1
            }
        case ADD_CHILD:
            return {
                ...state,
                child: state.child + 1
            }
        case ADD_INFANT:
            return {
                ...state,
                infant: state.infant + 1
            }
        case REMOVE_ADULT:
            return {
                ...state,
                adult: state.adult >= 2 ? state.adult - 1 : state.adult
            }
        case REMOVE_CHILD:
            return {
                ...state,
                child: state.child >= 1 ? state.child - 1 : state.child
            }
        case REMOVE_INFANT:
            return {
                ...state,
                infant: state.infant >=1 ? state.infant - 1 : state.infant
            }
        default:
            return state
    }
}


export {passengerState}
export default reducer