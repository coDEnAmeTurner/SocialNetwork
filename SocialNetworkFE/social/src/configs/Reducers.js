export const MyUserReducer = (current, action) => {
    switch (action.type) {
        case "login":
            return action.payload;
        case "logout":
            return null;
        default:
            return current;
    }
};

//QueryDocument
export const FrbUserReducer = (current, action) => {
    switch (action.type) {
        case "set":
            return action.payload;
        default:
            return current;
    }
};

export const ConversationsReducer = (current, action) => {
    switch (action.type) {
        case "set":
            return action.payload;
        default:
            return current;
    }
};

export const PickedConverReducer = (current, action) => {
    switch (action.type) {
        case "set":
            return action.payload;
        default:
            return current;
    }
};

export const ChosenConversationReducer = (current, action) => {
    switch (action.type) {
        case "set":
            return action.payload;
        default:
            return current;
    }
};

export const IsOpenPostReducer = (current, action) => {
    switch (action.type) {
        case "toggle":
            return !current;
        default:
            return current;
    }
};

export const IsOpenReducer = (current, action) => {
    switch (action.type) {
        case "toggle":
            return !current;
        default:
            return current;
    }
}

export const OpenMsgReducer = (current, action) => {
    switch (action.type) {
        case "toggle":
            return !current;
        default:
            return current;
    }
}

export const ChoicesReducer = (current, action) => {
    switch (action.type) {
        case "set":
            return action.payload;
        default:
            return current;
    }
}


export const QuestionsReducer = (current, action) => {
    switch (action.type) {
        case "set":
            return action.payload;
        default:
            return current;
    }
}

export const CommentsReducer = (current, action) => {
    switch (action.type) {
        case "set":
            return action.payload;
        default:
            return current;
    }
}

//fullpost action ds:
// {
//     type: "toggle"/"default"
//     payload: {
//         postId: post_id_here,
//         open: true/false
//     }
// }
export const FullPostReducer = (current, action) => {
    switch (action.type) {
        case "toggle": 
            return action.payload
        default:
            return current;
    }
}

//editpost action ds:
// {
//     type: "toggle"/"default"
//     payload: {
//         editPostId: edit_post_id_here,
//         open: true/false
//     }
// }
export const EditPostReducer = (current, action) => {
    switch (action.type) {
        case "toggle": 
            return action.payload
        default:
            return current;
    }
}

export const EditComReducer = (current, action) => {
    switch (action.type) {
        case "toggle": 
            return action.payload
        default:
            return current;
    }
}

export const IsEditReducer = (current, action) => {
    switch (action.type) {
        case "toggle": 
            return action.payload
        default:
            return current;
    }
}

export const RoomReducer = (current, action) => {
    switch (action.type) {
        case "toggle": 
            return action.payload
        default:
            return current;
    }
}

