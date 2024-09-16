import { Alert, Snackbar } from "@mui/material";
import { useState, useEffect } from "react";

interface MUISnackbarProps {
  readonly message: string;
  readonly type: "success" | "error" | "info";
  readonly open: boolean;
}

function MUISnackbar({ message, type, open }: MUISnackbarProps) {
  const [snackbarOpen, setSnackbarOpen] = useState(open);

  useEffect(() => {
    setSnackbarOpen(open);
  }, [open]);

  const handleClose = () => {
    setSnackbarOpen(false);
  };

  return (
    <Snackbar
      open={snackbarOpen}
      anchorOrigin={{
        vertical: "bottom",
        horizontal: "center",
      }}
      autoHideDuration={2000}
      onClose={handleClose}
    >
      <Alert
        severity={type}
        variant="filled"
        sx={{ width: "100%" }}
      >
        {message}
      </Alert>
    </Snackbar>
  );
}

export default MUISnackbar;
