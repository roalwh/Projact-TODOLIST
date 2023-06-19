
import './App.css';

// material-ui 컴포넌트 
import { Paper, List, Container } from '@mui/material';


// 컴포넌트 
import Todo from './Components/Todo';
import AddTodo from './Components/AddTodo';

function App() {
  // 출력할 내용 설정
  const items = [
    { id: "1", title: "hello World 1", done: true },
    { id: "1", title: "hello World 2", done: false }
  ];

  // items 길이만큼 출력
  let todoItems = items.length > 0 && (
    <Paper style={{ margin: '16' }} >
      <List>
        {
          //items 출력
          items.map((item, i) => {
            return (
              <Todo item={item} key={item.id} done={item.done} />
            )
          })
        }
      </List>
    </Paper>
  )

  return (
    <div className="App" >
      <Container maxWidth="md">
      <AddTodo></AddTodo>
      <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );
}

export default App;
