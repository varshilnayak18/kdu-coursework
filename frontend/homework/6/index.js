const express = require('express');
const cors = require('cors');

const app = express();
const PORT = process.env.PORT || 5000;
app.use(express.json());
app.use(cors());
app.use('/api/posts', require('./routes/api/posts'));

app.listen(PORT, () => console.log(`Server running on port ${PORT}`));