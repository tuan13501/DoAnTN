import Context from "./Context";
import {useContext} from "react";

function useStore() {
    const [state, dispatch] = useContext(Context)
    return [state, dispatch];
}

export {useStore};