const URL_BASE = 'http://localhost:8098';

const API_AUTH = URL_BASE + '/api/auth/'

const URL_BASE_API = URL_BASE + '/v1/api';

const API_USER = URL_BASE_API + '/user';

const API_ROLE = URL_BASE_API + '/role';

const API_SEARCH_USER = API_USER + '/search';

const HEADER = 'authorization';

export const url = {
    URL_BASE: URL_BASE,
    API_USER: API_USER,
    API_ROLE: API_ROLE,
    API_AUTH: API_AUTH,
    API_SEARCH_USER: API_SEARCH_USER,
    HEADER: HEADER,
}
