export default {
    get: jest.fn(() => Promise.resolve(["Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard"])),
    create: jest.fn(() => Promise.resolve({
        baseUrl: 'https://36a5-67-20-253-125.ngrok-free.app',
        headers: {"ngrok-skip-browser-warning": true, "Access-Control-Allow-Origin": "*"}
    }))
};