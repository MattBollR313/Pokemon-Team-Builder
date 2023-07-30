import './App.css';
import Layout from './components/Layout';
import {Routes, Route} from 'react-router-dom';
import Home from './components/home/Home';
import About from './components/about/About';
import Footer from './components/footer/Footer';
import Navigation from './components/navigation/Navigation';

function App() {

  return (
    <div className="App">
      <div className="content-wrap">
        <Navigation />
        <Routes>
          <Route path="/" element={<Layout/>}>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/about" element={<About/>}></Route>
          </Route>
        </Routes>
      </div>
      <Footer/>
    </div>
  );
  
}

export default App;
