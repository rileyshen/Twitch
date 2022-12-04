const SERVER_ORIGIN = '';

const loginUrl = `${SERVER_ORIGIN}/login`;

export const login = (credential) => {
    return fetch(loginUrl, {
        method: 'POST',
        headers: {
            'Contend-Type' : 'application/json',
        }, 
        credentials : 'include',
        body: JSON.stringify(credential)
    }).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to log in');
        }

        return response.json();
    })
}

const registerUrl = `${SERVER_ORIGIN}/register`;
export const register = (data) => {
    return fetch(registerUrl, {
        method: 'POST',
        headers: {
            'Contend-Type' : 'application/json',
        },
        body: JSON.stringify(data) 
    }).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to register');
        }
    })
}

const logoutUrl = `${SERVER_ORIGIN}/logout`;
export const logout = () => {
    return fetch(logoutUrl, {
        method: 'POST',
       credentials: 'include',
    }).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to log out');
        }
    })
}

const topGameUrl = `${SERVER_ORIGIN}/game`;

export const getTopGames = () => {
    return fetch(topGameUrl).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to get top games');
        }
        return response.json();
    })
}

const getGameDetailsUrl = `${SERVER_ORIGIN}/game?game_name=`;
export const getGamesDetails = (gameName) => {
    return fetch(`${getGameDetailsUrl}${gameName}`).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to find the games');
        }
        return response.json();
    })
}

const searchGameByIdUrl = `${SERVER_ORIGIN}/search?game_id=`;
export const searchGameById = (gameId) => {
    return fetch(`${searchGameByIdUrl}${gameId}`).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to find the games');
        }
        return response.json();
    })
}


export const searchGameByName = (gameName) => {
    return getGamesDetails(gameName).then((data) => {
        if (data && data.id) {
            return searchGameById(data.id);
        }
        return Error('Fail to find the games');
    })
}



const favoriteItemUrl = `${SERVER_ORIGIN}/favorite`;

export const addFavoriteItem = (favItem) => {
    return fetch(favoriteItemUrl, {
        method: 'POST',
        headers: {
            'Contend-Type' : 'application/json',
        },
        credentials : 'include',
        body: JSON.stringify({favorite: favItem}) 
    }).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to add favorite item');
        }
    })
}



export const deleteFavoriteItem = (favItem) => {
    return fetch(favoriteItemUrl, {
        method: 'DELETE',
        headers: {
            'Contend-Type' : 'application/json',
        },
        credentials : 'include',
        body: JSON.stringify({favorite: favItem}) 
    }).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to delete favorite item');
        }
    })
}


export const getFavoriteItem = (favItem) => {
    return fetch(favoriteItemUrl, {
        method: 'GET',
        headers: {
            'Contend-Type' : 'application/json',
        },
        credentials : 'include',
        body: JSON.stringify({favorite: favItem}) 
    }).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to get favorite item');
        }
        return response.json();
    })
}

const getRecommendedItemUrl = `${SERVER_ORIGIN}/recommendation`;
export const getRecommendations = () => {
    return fetch(getRecommendedItemUrl, {
        credentials : 'include',
    }).then((response) => {
        if (response.status !== 200) {
            throw Error('Fail to get recommended item');
        }
        return response.json();
    })
}